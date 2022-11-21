package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Vehicle
import com.google.gson.annotations.SerializedName

class VehicleResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Vehicle>?,
) {
    val resposta: VehicleResponse
        get() = VehicleResponse(
            count ?: 0,
            next ?: "",
            previous ?: "",
            results ?: emptyList()
        )
}