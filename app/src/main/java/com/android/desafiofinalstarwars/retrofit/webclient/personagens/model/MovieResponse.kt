package com.android.desafiofinalstarwars.retrofit.webclient.personagens.model

import com.android.desafiofinalstarwars.model.Movie
import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Movie>?,
) {
    val resposta: MovieResponse
        get() = MovieResponse(
            count ?: 0,
            next ?: "",
            previous ?: "",
            results ?: emptyList()
        )
}