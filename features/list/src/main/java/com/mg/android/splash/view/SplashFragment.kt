package com.mg.android.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.mg.android.common.base.BaseFragment
import com.mg.android.common.base.BaseViewModel
import com.mg.android.common.extensions.withDelay
import com.mg.android.list.R
import com.mg.android.splash.viewModel.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_splash, container, false)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        return view
    }

    override fun getViewModel(): BaseViewModel = viewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        withDelay(3000) {
            viewModel.openListFragment()

        }

    }

}
