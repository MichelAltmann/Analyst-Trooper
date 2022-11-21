package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Planet
import com.google.gson.annotations.SerializedName

class PlanetResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Planet>?,
) {
    val resposta: PlanetResponse
        get() = PlanetResponse(
            count ?: 0,
            next ?: "",
            previous ?: "",
            results ?: emptyList()
        )
}