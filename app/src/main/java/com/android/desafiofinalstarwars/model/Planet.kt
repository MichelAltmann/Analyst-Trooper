package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Planet(
    @SerializedName("name") val name: String?,
    @SerializedName("rotation_period") val rotationPeriod: String?,
    @SerializedName("orbital_period") val orbitalPeriod: String?,
    @SerializedName("diameter") val diameter: String?,
    @SerializedName("gravity") val gravity: String?,
    @SerializedName("terrain") val terrain: String?,
    @SerializedName("surface_water") val surfaceWater: String?,
    @SerializedName("population") val population: String?,
    @SerializedName("residents") val residents : List<String?>?,
    @SerializedName("films") val movies: List<String?>?,
    @SerializedName("created") val creationDate: String?,
    @SerializedName("edited") val editedDate: String?,
    @SerializedName("url") val url: String?,
): Serializable