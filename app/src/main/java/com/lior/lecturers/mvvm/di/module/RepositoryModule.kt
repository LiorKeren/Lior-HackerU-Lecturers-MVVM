package com.lior.lecturers.mvvm.di.module

import com.lior.lecturers.mvvm.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}