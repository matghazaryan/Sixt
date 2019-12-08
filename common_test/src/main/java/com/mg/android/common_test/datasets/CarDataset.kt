package com.mg.android.common_test.datasets

import com.mg.android.model.Car
import java.util.*

object CarDataset {

    val FAKE_CARS = listOf(
        Car(id="Id_1", modelName = "model_1", name = "name_1", make = "make_1", latitude = 40.34, longitude = 30.45, carImageUrl = "carImageUrl_1", transmission = "tranmission_1",fuelType = "fueltype_1", fuelLevel = "fuellevel_1"),
        Car(id="Id_2", modelName = "model_2", name = "name_2", make = "make_2", latitude = 40.34, longitude = 30.45, carImageUrl = "carImageUrl_2", transmission = "tranmission_2",fuelType = "fueltype_2", fuelLevel = "fuellevel_2"),
        Car(id="Id_3", modelName = "model_3", name = "name_3", make = "make_3", latitude = 40.34, longitude = 30.45, carImageUrl = "carImageUrl_3", transmission = "tranmission_3",fuelType = "fueltype_3", fuelLevel = "fuellevel_3")
    )
}