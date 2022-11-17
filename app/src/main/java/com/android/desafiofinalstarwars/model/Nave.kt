package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Nave(
    @SerializedName("name") val nome: String?,
    @SerializedName("model") val modelo: String?,
    @SerializedName("manufacturer") val manufatorador: String?,
    @SerializedName("cost_in_credits") val preco: String?,
    @SerializedName("length") val comprimento : String?,
    @SerializedName("max_atmosphering_speed") val velocidadeMaxima: String?,
    @SerializedName("crew") val capacidadeDePessoas: String?,
    @SerializedName("passengers") val passageiros : String?,
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
): Serializable