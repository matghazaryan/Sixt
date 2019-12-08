package com.mg.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Car(
    @PrimaryKey
    @SerializedName("id")
    val id: String = "",
    @SerializedName("modelName")
    val modelName: String  = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("make")
    val make: String = "",
    @SerializedName("latitude")
    val latitude: Double = 0.0,
    @SerializedName("longitude")
    val longitude: Double = 0.0,
    @SerializedName("carImageUrl")
    val carImageUrl: String = "",
    @SerializedName("transmission")
    val transmission: String = "",
    @SerializedName("fuelType")
    val fuelType: String = "",
    @SerializedName("fuelLevel")
    val fuelLevel: String = ""
) : Serializable{
     fun getRow1() : String{
        return "$modelName - $name ($transmission)"
    }

    fun getRow2() : String {
        return "Fuel -> $fuelType ($fuelLevel)"
    }
}