package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.*
import java.lang.Exception

class Repository(private val apiService: ApiService) : RepositoryInterface {

    override suspend fun getCharacters(page : Int): NetworkResponse<CharacterResponse> {
        return try {
            val response = apiService.getCharacters(page = page)
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun getStarships(page: Int): NetworkResponse<StarshipResponse> {
        return try {
            val response = apiService.getStarships(page = page)
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun getPlanets(): NetworkResponse<PlanetResponse> {
        return try {
            val response = apiService.getPlanets()
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun getMovies(): NetworkResponse<MovieResponse> {
        return try {
            val response = apiService.getMovies()
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun getSpecies(page: Int): NetworkResponse<SpecieResponse> {
        return try {
            val response = apiService.getSpecies(page = page)
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun getVehicles(page: Int): NetworkResponse<VehicleResponse> {
        return try {
            val response = apiService.getVehicles(page = page)
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
    suspend fun getCharacters(page : Int) : NetworkResponse<CharacterResponse>
    suspend fun getStarships(page: Int): NetworkResponse<StarshipResponse>
    suspend fun getPlanets() : NetworkResponse<PlanetResponse>
    suspend fun getMovies() : NetworkResponse<MovieResponse>
    suspend fun getSpecies(page: Int): NetworkResponse<SpecieResponse>
    suspend fun getVehicles(page: Int): NetworkResponse<VehicleResponse>
}