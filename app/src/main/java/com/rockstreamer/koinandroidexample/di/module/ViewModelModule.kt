package com.rockstreamer.koinandroidexample.di.module

import com.rockstreamer.koinandroidexample.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get() , get()) }
}