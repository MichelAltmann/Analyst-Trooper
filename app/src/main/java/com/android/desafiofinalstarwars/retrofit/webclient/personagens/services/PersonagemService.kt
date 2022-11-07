package com.android.desafiofinalstarwars.retrofit.webclient.personagens.services

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PersonagemResposta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonagemService {
    @GET("people/")
    suspend fun buscaPersonagens() : Response<PersonagemResposta>
}