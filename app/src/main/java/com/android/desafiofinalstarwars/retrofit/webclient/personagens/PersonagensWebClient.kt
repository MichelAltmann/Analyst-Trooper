package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import android.content.ContentValues.TAG
import android.util.Log
import com.android.desafiofinalstarwars.retrofit.webclient.RetrofitInicializador
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PersonagemResposta
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.services.PersonagemService
import java.lang.Exception

class PersonagensWebClient {

    private val personagemService : PersonagemService = RetrofitInicializador().personagemService

    suspend fun buscaPersonagens() : PersonagemResposta? {
        return try {
            val response = personagemService.buscaPersonagens()
            if (response.isSuccessful){
                response.body()
            } else {
                null
            }
        } catch (e: Exception){
            Log.e(TAG, "buscaPersonagens: ", e)
            null
        }
    }

}