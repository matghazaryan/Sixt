package com.mg.android.repository

import androidx.lifecycle.LiveData
import com.mg.android.local.dao.CarDao
import com.mg.android.model.Car
import com.mg.android.remote.CarDatasource
import com.mg.android.repository.utils.NetworkBoundResource
import com.mg.android.repository.utils.Resource
import kotlinx.coroutines.Deferred

interface CarRepository {
    suspend fun getCars(): LiveData<Resource<List<Car>>>
}

class CarRepositoryImpl(private val datasource: CarDatasource,
                        private val dao: CarDao): CarRepository{
    override suspend fun getCars(): LiveData<Resource<List<Car>>> {
        return object : NetworkBoundResource<List<Car>, List<Car>>() {

            override fun processResponse(response: List<Car>): List<Car>
                    = response

            override suspend fun saveCallResults(items: List<Car>)
                    = dao.save(items)

            override fun shouldFetch(data: List<Car>?): Boolean
                    = data == null || data.isEmpty()

            override suspend fun loadFromDb(): List<Car>
                    = dao.getCars()

            override fun createCallAsync(): Deferred<List<Car>>
                    = datasource.fetchCars()

        }.build().asLiveData()
    }

}