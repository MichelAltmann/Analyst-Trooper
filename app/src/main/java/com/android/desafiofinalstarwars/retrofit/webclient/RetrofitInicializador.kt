package com.android.desafiofinalstarwars.retrofit.webclient

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.services.PersonagemService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInicializador {
    private val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://swapi.dev/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val personagemService = retrofit.create(PersonagemService::class.java)
}