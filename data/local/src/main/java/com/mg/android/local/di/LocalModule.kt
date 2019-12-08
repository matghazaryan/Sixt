package com.mg.android.local.di

import com.mg.android.local.SixtDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

private const val DATABASE = "DATABASE"

val localModule = module {
    single(DATABASE) { SixtDatabase.buildDatabase(androidContext()) }
    factory { (get(DATABASE) as SixtDatabase).carDao() }
}