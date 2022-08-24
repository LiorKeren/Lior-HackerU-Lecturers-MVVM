package com.lior.lecturers.mvvm.di.module

import com.lior.lecturers.mvvm.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get(),get())
    }
}