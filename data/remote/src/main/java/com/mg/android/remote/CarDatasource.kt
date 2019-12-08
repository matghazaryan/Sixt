package com.mg.android.remote

class CarDatasource(private val userService: CarService) {

    fun fetchCars() = userService.fetchCars()
}