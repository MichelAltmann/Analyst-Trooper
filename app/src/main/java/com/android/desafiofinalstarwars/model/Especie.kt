package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Especie(
    @SerializedName("name") val nome : String,
    @SerializedName("average_height") val alturaMedia: String,
    @SerializedName("average_lifespan") val mediaDeVida : String,
    @SerializedName("classification") val classificacao : String,
    @SerializedName("created") val dataCriacao : String,
    @SerializedName("designation") val designiacao : String,
    @SerializedName("edited") val dataEdicao : String,
    @SerializedName("eye_colors") val coresDeOlho : String,
    @SerializedName("films") val filmes : List<String>,
    @SerializedName("hair_colors") val coresDeCabelo : String,
    @SerializedName("homeworld") val planetaDeOrigem : String,
    @SerializedName("language") val ligua : String,
    @SerializedName("people") val pessoas : List<String>,
    @SerializedName("skin_colors") val coresDePele : String,
    @SerializedName("url") val url: String
) : Serializable