package com.android.desafiofinalstarwars.model

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PersonagemResposta
import com.google.gson.annotations.SerializedName

data class Personagem(
    @SerializedName("birth_year") val anoNascimento: String?,
    @SerializedName("eye_color") val corDoOlho: String?,
    @SerializedName("filmes") val filmes: List<String?>?,
    @SerializedName("gender") val genero: String?,
    @SerializedName("hair_color") val corDoCabelo: String?,
    @SerializedName("height") val altura: Int?,
    @SerializedName("homeworld") val planetaOrigem: String?,
    @SerializedName("mass") val peso: Int?,
    @SerializedName("name") val nome: String?,
    @SerializedName("skin_color") val corDePele: String?,
    @SerializedName("created") val dataCriacao: String?,
    @SerializedName("edited") val dataEditado: String?,
    @SerializedName("species") val especies: List<String?>?,
    @SerializedName("starships") val naves: List<String?>?,
    @SerializedName("url") val url: String?,
    @SerializedName("vehicles") val veiculos: List<String?>?
) {
    val personagem: Personagem
        get() = Personagem(
            anoNascimento ?: "",
            corDoOlho ?: "",
            filmes ?: emptyList(),
            genero ?: "",
            corDoCabelo ?: "",
            altura ?: 0,
            planetaOrigem ?: "",
            peso ?: 0,
            nome ?: "",
            corDePele ?: "",
            dataCriacao ?: "",
            dataEditado ?: "",
            especies ?: emptyList(),
            naves ?: emptyList(),
            url ?: "",
            veiculos ?: emptyList()
        )
}