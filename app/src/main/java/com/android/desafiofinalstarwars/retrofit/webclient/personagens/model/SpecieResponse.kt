package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Specie
import com.google.gson.annotations.SerializedName

class SpecieResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Specie>?,
) {
    val resposta: SpecieResponse
        get() = SpecieResponse(
            count ?: 0,
            next ?: "",
            previous ?: "",
            results ?: emptyList()
        )
}