package com.mg.android.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mg.android.model.Car

@Dao
abstract class CarDao: BaseDao<Car>() {

    @Query("SELECT * FROM Car ORDER BY name")
    abstract suspend fun getCars(): List<Car>



    suspend fun save(car: Car) {
        insert(car.apply { })
    }

    suspend fun save(users: List<Car>) {
        insert(users.apply { forEach { } })
    }
}