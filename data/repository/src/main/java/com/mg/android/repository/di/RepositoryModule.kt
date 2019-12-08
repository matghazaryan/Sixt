package com.mg.android.repository.di

import com.mg.android.repository.AppDispatchers
import com.mg.android.repository.CarRepository
import com.mg.android.repository.CarRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory { CarRepositoryImpl(get(), get()) as CarRepository }
}