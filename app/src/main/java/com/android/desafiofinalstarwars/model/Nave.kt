package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName

data class Nave(
    @SerializedName("name") val nome: String?,
    @SerializedName("model") val modelo: String?,
    @SerializedName("manufacturer") val manufatorador: Int?,
    @SerializedName("cost_in_credits") val preco: Int?,
    @SerializedName("lenght") val comprimento : Int?,
    @SerializedName("max_atmosphering_speed") val velocidadeMaxima: Int?,
    @SerializedName("crew") val capacidadeDePessoas: Int?,
    @SerializedName("cargo_capacity") val capacidade: String?,
    @SerializedName("consumables") val consumiveis: String?,
    @SerializedName("hyperdrive_rating") val avaliacao: String?,
    @SerializedName("MGLT") val MGLT: String?,
    @SerializedName("starship_class") val classe: String?,
    @SerializedName("pilots") val pilotos : List<String?>?,
    @SerializedName("films") val filmes: List<String?>?,
    @SerializedName("created") val dataCriacao: String?,
    @SerializedName("edited") val dataEditado: String?,
    @SerializedName("url") val url: String?,
)