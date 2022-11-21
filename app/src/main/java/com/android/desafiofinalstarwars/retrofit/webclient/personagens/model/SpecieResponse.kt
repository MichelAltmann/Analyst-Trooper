package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Specie
import com.google.gson.annotations.SerializedName

class SpecieResponse(
    @SerializedName("count") val quantia: Int?,
    @SerializedName("next") val proximo: String?,
    @SerializedName("previous") val anterior: String?,
    @SerializedName("results") val resultados: List<Specie>?,
) {
    val resposta: SpecieResponse
        get() = SpecieResponse(
            quantia ?: 0,
            proximo ?: "",
            anterior ?: "",
            resultados ?: emptyList()
        )
}