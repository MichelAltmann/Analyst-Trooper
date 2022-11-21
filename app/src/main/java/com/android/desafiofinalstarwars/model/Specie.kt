package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Specie(
    @SerializedName("name") val name: String?,
    @SerializedName("average_height") val averageHeight: String?,
    @SerializedName("average_lifespan") val averageLifespan: String?,
    @SerializedName("classification") val classification: String?,
    @SerializedName("created") val createdDate: String?,
    @SerializedName("designation") val designation: String?,
    @SerializedName("edited") val editedDate: String?,
    @SerializedName("eye_colors") val eyeColors: String?,
    @SerializedName("films") val movies: List<String>?,
    @SerializedName("hair_colors") val hairColors: String?,
    @SerializedName("homeworld") val homeworld: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("people") val people: List<String>?,
    @SerializedName("skin_colors") val skinColors: String?,
    @SerializedName("url") val url: String?
) : Serializable