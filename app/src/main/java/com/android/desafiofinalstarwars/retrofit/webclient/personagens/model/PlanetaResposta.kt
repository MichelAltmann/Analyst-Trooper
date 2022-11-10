package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Personagem
import com.android.desafiofinalstarwars.model.Planeta
import com.google.gson.annotations.SerializedName

class PlanetaResposta(
    @SerializedName("count") val quantia: Int?,
    @SerializedName("next") val proximo: String?,
    @SerializedName("previous") val anterior: String?,
    @SerializedName("results") val resultados: List<Planeta>?,
) {
    val resposta: PlanetaResposta
        get() = PlanetaResposta(
            quantia ?: 0,
            proximo ?: "",
            anterior ?: "",
            resultados ?: emptyList()
        )
}