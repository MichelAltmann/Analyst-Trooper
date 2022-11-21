package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Veiculo
import com.google.gson.annotations.SerializedName

class VeiculoResposta(
    @SerializedName("count") val quantia: Int?,
    @SerializedName("next") val proximo: String?,
    @SerializedName("previous") val anterior: String?,
    @SerializedName("results") val resultados: List<Veiculo>?,
) {
    val resposta: VeiculoResposta
        get() = VeiculoResposta(
            quantia ?: 0,
            proximo ?: "",
            anterior ?: "",
            resultados ?: emptyList()
        )
}