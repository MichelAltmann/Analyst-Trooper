package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Starship(
    @SerializedName("name") val name: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("cost_in_credits") val price: String?,
    @SerializedName("length") val length : String?,
    @SerializedName("max_atmosphering_speed") val maxSpeed: String?,
    @SerializedName("crew") val crewCapacity: String?,
    @SerializedName("passengers") val passengers : String?,
    @SerializedName("cargo_capacity") val cargoCapacity: String?,
    @SerializedName("consumables") val consumables: String?,
    @SerializedName("hyperdrive_rating") val rating: String?,
    @SerializedName("MGLT") val MGLT: String?,
    @SerializedName("starship_class") val `class`: String?,
    @SerializedName("pilots") val pilots : List<String?>?,
    @SerializedName("films") val movies: List<String?>?,
    @SerializedName("created") val creationDate: String?,
    @SerializedName("edited") val editedDate: String?,
    @SerializedName("url") val url: String?,
): Serializable