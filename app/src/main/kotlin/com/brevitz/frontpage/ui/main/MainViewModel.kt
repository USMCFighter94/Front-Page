package com.brevitz.frontpage.ui.main

import androidx.lifecycle.ViewModel
import com.brevitz.frontpage.data.PostDataResponse
import com.brevitz.frontpage.network.Endpoints
import com.brevitz.frontpage.network.models.RedditPostData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val endpoints: Endpoints) : ViewModel() {

    private val loadingSubject = PublishSubject.create<Boolean>()
    private val dataSubject = PublishSubject.create<PostDataResponse>()
    private val disposables = CompositeDisposable()

    fun loadingObservable(): Observable<Boolean> = loadingSubject
    fun dataObservable(): Observable<PostDataResponse> = dataSubject

    fun getData(after: String) {
        loadingSubject.onNext(true)

        disposables.add(endpoints.getFrontPage(after)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doFinally { loadingSubject.onNext(false) }
            .subscribeBy(onSuccess = {
                val data = ArrayList<RedditPostData>()

                for (child in it.data.children) {
                    data.add(child.data)
                }

                dataSubject.onNext(PostDataResponse.Success(data, it.data.after))
            }, onError = { dataSubject.onNext(PostDataResponse.Error(it)) }
            )
        )
    }
}
