package com.brevitz.frontpage.modules

import com.brevitz.frontpage.network.Endpoints
import com.brevitz.frontpage.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { createRetrofit() }

    viewModel { MainViewModel(createEndpoints(get())) }
}

private fun createRetrofit() = Retrofit.Builder()
    .baseUrl("https://www.reddit.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

private fun createEndpoints(retrofit: Retrofit) = retrofit.create(Endpoints::class.java)
