package com.brevitz.frontpage.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.brevitz.frontpage.R
import com.brevitz.frontpage.data.PostDataResponse.Error
import com.brevitz.frontpage.data.PostDataResponse.Success
import com.brevitz.frontpage.hide
import com.brevitz.frontpage.show
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val TAG = MainFragment::class.java.simpleName

    private val viewModel: MainViewModel by viewModel()
    private val postAdapter = PostAdapter(arrayListOf())
    private val disposables = CompositeDisposable()

    private var lastItem = 0
    private var afterId = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
        }

        disposables.addAll(viewModel.loadingObservable().subscribe {
            if (it) mainProgressBar.show() else mainProgressBar.hide()
        }, viewModel.dataObservable().subscribe {
            when (it) {
                is Success -> {
                    postAdapter.postList.addAll(it.data)
                    postAdapter.notifyItemRangeInserted(lastItem, postAdapter.postList.size - 1)
                    lastItem = postAdapter.postList.size
                    afterId = it.after
                }
                is Error -> {
                    Log.e(TAG, "Error!", it.throwable)

                    AlertDialog.Builder(requireContext())
                        .setTitle("Error!")
                        .setMessage(it.throwable.localizedMessage)
                        .create().show()
                }
            }
        })

        viewModel.getData(afterId)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
