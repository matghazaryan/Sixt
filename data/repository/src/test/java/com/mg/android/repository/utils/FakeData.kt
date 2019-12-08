package com.mg.android.repository.utils

import com.mg.android.model.Car

object FakeData {
    fun createFakeCars(count: Int): List<Car> {
        return (0 until count).map {
            createFakeCar(it.toString())
        }
    }

    fun createFakeCar(id: String): Car {
        return Car(id="Id$id", modelName = "model_1", name = "name_1", make = "make_1", latitude = 40.34, longitude = 30.45, carImageUrl = "carImageUrl_1")

    }
}