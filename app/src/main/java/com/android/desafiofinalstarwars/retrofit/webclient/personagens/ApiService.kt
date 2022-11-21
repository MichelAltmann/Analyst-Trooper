package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.*
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("people/")
    suspend fun buscaPersonagens() : Response<CharacterResponse>

    @GET("starships/")
    suspend fun buscaNaves() : Response<StarshipResponse>

    @GET("planets/")
    suspend fun buscaPlanetas() : Response<PlanetaResposta>

    @GET("species/")
    suspend fun buscaEspecies() : Response<SpecieResponse>

    @GET("films/")
    suspend fun buscaFilmes() : Response<FilmeResposta>

    @GET("vehicles/")
    suspend fun buscaVeiculos() : Response<VeiculoResposta>
}