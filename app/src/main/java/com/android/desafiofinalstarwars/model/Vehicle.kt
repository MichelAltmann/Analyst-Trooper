package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Vehicle(
    @SerializedName("cargo_capacity") val cargoCapacity : String,
    @SerializedName("consumables") val consumables : String,
    @SerializedName("cost_in_credits") val price : String,
    @SerializedName("created") val creationDate : String,
    @SerializedName("crew") val crewCapacity : String,
    @SerializedName("edited") val editedDate : String,
    @SerializedName("films") val movies : List<String>,
    @SerializedName("length") val length : String,
    @SerializedName("manufacturer") val manufacturer : String,
    @SerializedName("max_atmosphering_speed") val maxSpeed : String,
    @SerializedName("model") val model : String,
    @SerializedName("name") val name : String,
    @SerializedName("passengers") val passengers : String,
    @SerializedName("pilots") val pilots : List<Any>,
    @SerializedName("url") val url: String,
    @SerializedName("vehicle_class") val vehicleClass : String
): Serializable