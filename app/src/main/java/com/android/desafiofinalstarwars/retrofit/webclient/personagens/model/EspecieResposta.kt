package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Especie
import com.android.desafiofinalstarwars.model.Planeta
import com.google.gson.annotations.SerializedName

class EspecieResposta(
    @SerializedName("count") val quantia: Int?,
    @SerializedName("next") val proximo: String?,
    @SerializedName("previous") val anterior: String?,
    @SerializedName("results") val resultados: List<Especie>?,
) {
    val resposta: EspecieResposta
        get() = EspecieResposta(
            quantia ?: 0,
            proximo ?: "",
            anterior ?: "",
            resultados ?: emptyList()
        )
}