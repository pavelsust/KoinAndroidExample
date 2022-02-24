package com.rockstreamer.koinandroidexample.di.module

import com.rockstreamer.koinandroidexample.data.repository.MainRepository
import org.koin.dsl.module
import kotlin.math.sin


val repoModule = module {
    single { MainRepository(get() ) }
}