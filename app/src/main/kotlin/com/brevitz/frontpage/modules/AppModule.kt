package com.brevitz.frontpage.modules

import com.brevitz.frontpage.network.Rest
import com.brevitz.frontpage.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { Rest() }

    viewModel { MainViewModel(get()) }
}
