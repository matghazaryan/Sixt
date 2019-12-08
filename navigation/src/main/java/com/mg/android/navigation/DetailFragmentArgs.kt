package com.mg.android.navigation

import android.os.Bundle
import androidx.navigation.NavArgs
import com.mg.android.model.Car
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

data class DetailFragmentArgs(val car: Car) : NavArgs {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putSerializable("car", this.car)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): DetailFragmentArgs {
            bundle.setClassLoader(DetailFragmentArgs::class.java.classLoader)
            return DetailFragmentArgs(bundle.getSerializable("car") as Car)
        }
    }
}
