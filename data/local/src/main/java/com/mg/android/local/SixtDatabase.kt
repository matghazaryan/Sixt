package com.mg.android.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mg.android.local.dao.CarDao
import com.mg.android.model.Car

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class SixtDatabase: RoomDatabase() {

    // DAO
    abstract fun carDao(): CarDao

    companion object {

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, SixtDatabase::class.java, "Sixt-mg.db")
                .build()
    }
}