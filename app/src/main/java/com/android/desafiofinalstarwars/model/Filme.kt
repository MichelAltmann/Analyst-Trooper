package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName

data class Filme(
    @SerializedName("characters") val personagens : List<String>,
    @SerializedName("created") val dataCriacao : String,
    @SerializedName("director") val diretor : String,
    @SerializedName("edited") val dataEdicao : String,
    @SerializedName("episode_id") val id : String?,
    @SerializedName("opening_crawl") val fraseAbertura : String,
    @SerializedName("planets") val planetas : List<String>,
    @SerializedName("producer") val produtor : String,
    @SerializedName("release_date") val dataLancamento : String,
    @SerializedName("species") val especies : List<String>,
    @SerializedName("starships") val naves : List<String>,
    @SerializedName("title") val titulo : String,
    @SerializedName("url") val url: String,
    @SerializedName("vehicles") val veiculos : List<String>
)