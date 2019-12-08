package com.mg.android.remote

import com.mg.android.model.Car
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CarService {

    @GET("cars")
    fun fetchCars(): Deferred<List<Car>>
}