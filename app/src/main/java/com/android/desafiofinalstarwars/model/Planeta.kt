package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Planeta(
    @SerializedName("name") val nome: String?,
    @SerializedName("rotation_period") val periodoDeRotacao: String?,
    @SerializedName("orbital_period") val periodoOrbital: String?,
    @SerializedName("diameter") val diametro: String?,
    @SerializedName("gravity") val gravidade: String?,
    @SerializedName("terrain") val terreno: String?,
    @SerializedName("surface_water") val aguaNaSuperficie: String?,
    @SerializedName("population") val populacao: String?,
    @SerializedName("residents") val residentes : List<String?>?,
    @SerializedName("films") val filmes: List<String?>?,
    @SerializedName("created") val dataCriacao: String?,
    @SerializedName("edited") val dataEditado: String?,
    @SerializedName("url") val url: String?,
): Serializable