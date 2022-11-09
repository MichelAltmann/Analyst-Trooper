package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import com.android.desafiofinalstarwars.retrofit.webclient.RetrofitInicializador
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.ApiError
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.NetworkResponse
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.PersonagemResposta
import retrofit2.Response
import java.lang.Exception

class Repository(private val apiService: ApiService) : RepositoryInterface {

//    override suspend fun buscaPersonagens(): Response<PersonagemResposta> = retrofitInicializador.service.buscaPersonagens()
    override suspend fun buscaPersonagens(): NetworkResponse<PersonagemResposta> {
        return try {
            val response = apiService.buscaPersonagens()
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }
}

interface RepositoryInterface {
    suspend fun buscaPersonagens() : NetworkResponse<PersonagemResposta>

}