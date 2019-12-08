package com.mg.android.remote

import com.mg.android.remote.base.BaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection

class CarServiceTest: BaseTest(){
    @Test
    fun `get cars `() {
        mockHttpResponse(mockServer, "cars.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val cars = carService.fetchCars().await()
//            Assert.assertEquals(1, cars.size)
            Assert.assertEquals("WMWSW31030T222518", cars.first().id)
            Assert.assertEquals("Vanessa", cars.first().name)
            Assert.assertEquals("https://cdn.sixt.io/codingtask/images/mini.png", cars.first().carImageUrl)
        }
    }

    @Test(expected = HttpException::class)
    fun `get cars and fail`() {
        mockHttpResponse(mockServer,"cars.json", HttpURLConnection.HTTP_FORBIDDEN)
        runBlocking {
            carService.fetchCars().await()
        }
    }
}