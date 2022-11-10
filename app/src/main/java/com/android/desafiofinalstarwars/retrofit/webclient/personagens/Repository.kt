package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import android.content.ContentValues.TAG
import android.util.Log
import com.android.desafiofinalstarwars.model.Especie
import com.android.desafiofinalstarwars.model.Veiculo
import com.android.desafiofinalstarwars.retrofit.webclient.RetrofitInicializador
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.*
import retrofit2.Response
import java.lang.Exception
import kotlin.math.log

class Repository(private val apiService: ApiService) : RepositoryInterface {

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

    override suspend fun buscaNaves(): NetworkResponse<NaveResposta> {
        return try {
            val response = apiService.buscaNaves()
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun buscaPlanetas(): NetworkResponse<PlanetaResposta> {
        return try {
            val response = apiService.buscaPlanetas()
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun buscaFilmes(): NetworkResponse<FilmeResposta> {
        return try {
            val response = apiService.buscaFilmes()
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun buscaEspecies(): NetworkResponse<EspecieResposta> {
        return try {
            val response = apiService.buscaEspecies()
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun buscaVeiculos(): NetworkResponse<VeiculoResposta> {
        return try {
            val response = apiService.buscaVeiculos()
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
    suspend fun buscaNaves() : NetworkResponse<NaveResposta>
    suspend fun buscaPlanetas() : NetworkResponse<PlanetaResposta>
    suspend fun buscaFilmes() : NetworkResponse<FilmeResposta>
    suspend fun buscaEspecies() : NetworkResponse<EspecieResposta>
    suspend fun buscaVeiculos() : NetworkResponse<VeiculoResposta>
}