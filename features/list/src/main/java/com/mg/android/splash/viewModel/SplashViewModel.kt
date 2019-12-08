package com.mg.android.splash.viewModel

import com.mg.android.common.base.BaseViewModel
import com.mg.android.splash.view.SplashFragmentDirections

class SplashViewModel() : BaseViewModel(){
    fun openListFragment() {
        navigate(SplashFragmentDirections.actionSplashFragmentToListFragment())
    }

    init {

    }
}