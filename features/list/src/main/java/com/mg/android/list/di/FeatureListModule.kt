package com.mg.android.list.di

import com.mg.android.list.domain.GetCarUseCase
import com.mg.android.list.viewmodel.ListViewModel
import com.mg.android.splash.viewModel.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureListModule = module {
    factory { GetCarUseCase(get()) }
    viewModel { ListViewModel(get(), get()) }
    viewModel { SplashViewModel() }
}