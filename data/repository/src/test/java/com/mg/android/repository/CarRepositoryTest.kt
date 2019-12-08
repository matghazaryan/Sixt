package com.mg.android.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mg.android.local.dao.CarDao
import com.mg.android.model.Car
import com.mg.android.remote.CarDatasource
import com.mg.android.repository.CarRepository
import com.mg.android.repository.CarRepositoryImpl
import com.mg.android.repository.utils.Resource
import io.mockk.*
import io.philippeboisney.common_test.rules.CoroutinesMainDispatcherRule
import com.mg.android.repository.utils.FakeData
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CarRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesMainDispatcherRule = CoroutinesMainDispatcherRule()

    // FOR DATA
    private lateinit var observer: Observer<Resource<List<Car>>>
    private lateinit var observerCar: Observer<Resource<Car>>
    private lateinit var carRepository: CarRepository
    private val carService = mockk<CarDatasource>()
    private val carDao = mockk<CarDao>(relaxed = true)

    @Before
    fun setUp() {
        observer = mockk(relaxed = true)
        observerCar = mockk(relaxed = true)
        carRepository = CarRepositoryImpl(carService, carDao)
    }

    @Test
    fun `Get cars from server when no internet is available`() {
        val exception = Exception("Internet")
        every { carService.fetchCars() } throws exception
        coEvery { carDao.getCars() } returns listOf()

        runBlocking {
            carRepository.getCars().observeForever(observer)
        }

        verifyOrder {
            observer.onChanged(Resource.loading(null))
            observer.onChanged(Resource.loading(listOf()))
            observer.onChanged(Resource.error(exception, listOf()))
        }
        confirmVerified(observer)
    }


    @Test
    fun `Get cars from network`() {
        val fakeCars = FakeData.createFakeCars(5)
        every { carService.fetchCars() } returns GlobalScope.async {fakeCars }
        coEvery { carDao.getCars() } returns listOf() andThen { fakeCars }

        runBlocking {
            carRepository.getCars().observeForever(observer)
        }

        verifyOrder {
            observer.onChanged(Resource.loading(null))
            observer.onChanged(Resource.loading(listOf()))
            observer.onChanged(Resource.success(fakeCars))
        }

        coVerify(exactly = 1) {
            carDao.save(fakeCars)
        }

        confirmVerified(observer)
    }

    @Test
    fun `Get cars from db`() {
        val fakeCars = FakeData.createFakeCars(5)
        every { carService.fetchCars() } returns GlobalScope.async {fakeCars }
        coEvery { carDao.getCars() } returns fakeCars

        runBlocking {
            carRepository.getCars().observeForever(observer)
        }

        verifyOrder {
            observer.onChanged(Resource.loading(null))
            observer.onChanged(Resource.success(fakeCars))
        }

        confirmVerified(observer)
    }
}