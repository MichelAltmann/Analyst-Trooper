package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Filme
import com.google.gson.annotations.SerializedName

class FilmeResposta(
    @SerializedName("count") val quantia: Int?,
    @SerializedName("next") val proximo: String?,
    @SerializedName("previous") val anterior: String?,
    @SerializedName("results") val resultados: List<Filme>?,
) {
    val resposta: FilmeResposta
        get() = FilmeResposta(
            quantia ?: 0,
            proximo ?: "",
            anterior ?: "",
            resultados ?: emptyList()
        )
}