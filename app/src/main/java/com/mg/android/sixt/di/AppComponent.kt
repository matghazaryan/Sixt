package com.mg.android.sixt.di

import com.mg.android.list.di.featureListModule
import com.mg.android.local.di.localModule
import com.mg.android.remote.di.createRemoteModule
import com.mg.android.repository.di.repositoryModule


val appComponent= listOf(createRemoteModule("https://cdn.sixt.io/codingtask/"), repositoryModule, featureListModule, localModule)