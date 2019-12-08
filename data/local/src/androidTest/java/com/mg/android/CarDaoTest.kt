package com.mg.android

import com.mg.android.common_test.datasets.CarDataset.FAKE_CARS
import com.mg.android.local.BaseTest
import com.mg.android.model.Car
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CarDaoTest: BaseTest() {

    override fun setUp(){
        super.setUp()
        fillDatabase()
    }

    @Test
    fun getCarsFromDb() = runBlocking {
        val users = database.carDao().getCars()
        assertEquals(3, users.size)
        compareTwoCars(FAKE_CARS.first(), users.first())
    }

    private fun compareTwoCars(car: Car, carToTest: Car){
        assertEquals(car.id, carToTest.id)
        assertEquals(car.name, carToTest.name)
        assertEquals(car.modelName, carToTest.modelName)
        assertEquals(car.make, carToTest.make)
        assertEquals(car.carImageUrl, carToTest.carImageUrl)
    }

    private fun fillDatabase() {
        runBlocking {
            database.carDao().save(FAKE_CARS)
        }
    }
}