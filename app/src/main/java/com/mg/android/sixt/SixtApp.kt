package com.mg.android.sixt

import android.app.Application
import com.mg.android.sixt.di.appComponent
import org.koin.android.ext.android.startKoin

open class SixtApp: Application() {
    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    open fun configureDi() =
        startKoin(this, provideComponent())

    open fun provideComponent() = appComponent
}