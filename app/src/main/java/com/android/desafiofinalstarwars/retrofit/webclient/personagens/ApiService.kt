package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("people/")
    suspend fun buscaPersonagens() : Response<PersonagemResposta>

    @GET("planets/")
    suspend fun buscaPlanetas() : Response<PlanetaResposta>

    @GET("starships/")
    suspend fun buscaNaves() : Response<NaveResposta>

    @GET("species/")
    suspend fun buscaEspecies() : Response<EspecieResposta>

    @GET("films/")
    suspend fun buscaFilmes() : Response<FilmeResposta>

    @GET("vehicles/")
    suspend fun buscaVeiculos() : Response<VeiculoResposta>
}