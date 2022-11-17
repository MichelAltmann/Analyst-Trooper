package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Veiculo(
    @SerializedName("cargo_capacity") val capacidadeDeCarga : String,
    @SerializedName("consumables") val consumiveis : String,
    @SerializedName("cost_in_credits") val preco : String,
    @SerializedName("created") val dataCriacao : String,
    @SerializedName("crew") val tripulantes : String,
    @SerializedName("edited") val dataEdicao : String,
    @SerializedName("films") val filmes : List<String>,
    @SerializedName("length") val comprimento : String,
    @SerializedName("manufacturer") val manufaturador : String,
    @SerializedName("max_atmosphering_speed") val velocidadeMaxima : String,
    @SerializedName("model") val modelo : String,
    @SerializedName("name") val nome : String,
    @SerializedName("passengers") val passageiros : String,
    @SerializedName("pilots") val pilotos : List<Any>,
    @SerializedName("url") val url: String,
    @SerializedName("vehicle_class") val classe : String
): Serializable