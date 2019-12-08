package com.mg.android.list.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mg.android.model.Car
import com.mg.android.repository.CarRepository
import com.mg.android.repository.utils.Resource

class GetCarUseCase(private val repository: CarRepository) {

    suspend operator fun invoke(): LiveData<Resource<List<Car>>> {
        return Transformations.map(repository.getCars()) {
            it
        }
    }
}