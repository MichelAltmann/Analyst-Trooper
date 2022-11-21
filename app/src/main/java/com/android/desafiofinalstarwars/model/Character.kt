package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(
    @SerializedName("birth_year") val birthYear: String?,
    @SerializedName("eye_color") val eyeColor: String?,
    @SerializedName("filmes") val movies: List<String?>?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("hair_color") val hairColor: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("homeworld") val homeworld: String?,
    @SerializedName("mass") val mass: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("skin_color") val skinColor: String?,
    @SerializedName("created") val creationDate: String?,
    @SerializedName("edited") val editedDate: String?,
    @SerializedName("species") val species: List<String?>?,
    @SerializedName("starships") val starships: List<String?>?,
    @SerializedName("url") val url: String?,
    @SerializedName("vehicles") val vehicles: List<String?>?
) : Serializable