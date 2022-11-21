package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Starship
import com.google.gson.annotations.SerializedName

class StarshipResponse(
    @SerializedName("count") val quantia: Int?,
    @SerializedName("next") val proximo: String?,
    @SerializedName("previous") val anterior: String?,
    @SerializedName("results") val resultados: List<Starship>?,
)