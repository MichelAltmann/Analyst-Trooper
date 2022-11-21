package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.*
import java.lang.Exception

class Repository(private val apiService: ApiService) : RepositoryInterface {

    override suspend fun getCharacters(): NetworkResponse<CharacterResponse> {
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

    override suspend fun getStarships(): NetworkResponse<StarshipResponse> {
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

    override suspend fun getSpecies(): NetworkResponse<SpecieResponse> {
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
    suspend fun getCharacters() : NetworkResponse<CharacterResponse>
    suspend fun getStarships() : NetworkResponse<StarshipResponse>
    suspend fun buscaPlanetas() : NetworkResponse<PlanetaResposta>
    suspend fun buscaFilmes() : NetworkResponse<FilmeResposta>
    suspend fun getSpecies() : NetworkResponse<SpecieResponse>
    suspend fun buscaVeiculos() : NetworkResponse<VeiculoResposta>
}