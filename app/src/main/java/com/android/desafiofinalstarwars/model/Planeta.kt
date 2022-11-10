package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName

data class Planeta(
    @SerializedName("name") val nome: String?,
    @SerializedName("rotation_period") val periodoDeRotacao: Int?,
    @SerializedName("orbital_period") val periodoOrbital: Int?,
    @SerializedName("diameter") val diametro: Int?,
    @SerializedName("gravity") val gravidade: String?,
    @SerializedName("terrain") val terreno: String?,
    @SerializedName("surface_water") val aguaNaSuperficie: Int?,
    @SerializedName("population") val populacao: Int?,
    @SerializedName("residents") val residentes : List<String?>?,
    @SerializedName("films") val filmes: List<String?>?,
    @SerializedName("created") val dataCriacao: String?,
    @SerializedName("edited") val dataEditado: String?,
    @SerializedName("url") val url: String?,
)