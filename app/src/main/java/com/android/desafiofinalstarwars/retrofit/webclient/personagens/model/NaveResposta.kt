package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Nave
import com.android.desafiofinalstarwars.model.Personagem
import com.google.gson.annotations.SerializedName

class NaveResposta(
    @SerializedName("count") val quantia: Int?,
    @SerializedName("next") val proximo: String?,
    @SerializedName("previous") val anterior: String?,
    @SerializedName("results") val resultados: List<Nave>?,
) {
    val resposta: NaveResposta
        get() = NaveResposta(
            quantia ?: 0,
            proximo ?: "",
            anterior ?: "",
            resultados ?: emptyList()
        )
}