package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Personagem
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class PersonagemResposta(
    @SerializedName("count") val quantia: Int?,
    @SerializedName("next") val proximo: String?,
    @SerializedName("previous") val anterior: String?,
    @SerializedName("results") val resultados: List<Personagem>?,
) {
    val resposta: PersonagemResposta
        get() = PersonagemResposta(
            quantia ?: 0,
            proximo ?: "",
            anterior ?: "",
            resultados ?: emptyList()
        )
}