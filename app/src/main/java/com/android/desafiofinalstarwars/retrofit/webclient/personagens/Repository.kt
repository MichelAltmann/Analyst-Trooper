package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.*

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

    override suspend fun getCharactersSearch(filter: String, page : Int) : NetworkResponse<CharacterResponse>{
        return try {
            val response = apiService.getCharactersSearch(filter = filter, page = page)
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

    override suspend fun getSpeciesSearch(filter: String, page : Int) : NetworkResponse<SpecieResponse>{
        return try {
            val response = apiService.getSpeciesSearch(filter = filter, page = page)
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

    override suspend fun getStarshipsSearch(filter: String, page : Int) : NetworkResponse<StarshipResponse>{
        return try {
            val response = apiService.getStarshipsSearch(filter = filter, page = page)
            if (response.isSuccessful){
                NetworkResponse.Success(response.body()!!)
            }else{
                NetworkResponse.Failed(Exception())
            }
        } catch (e : Exception){
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun getPlanets(page: Int): NetworkResponse<PlanetResponse> {
        return try {
            val response = apiService.getPlanets(page = page)
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
    suspend fun getCharactersSearch(filter: String, page: Int) : NetworkResponse<CharacterResponse>
    suspend fun getSpecies(page: Int): NetworkResponse<SpecieResponse>
    suspend fun getSpeciesSearch(filter: String, page: Int): NetworkResponse<SpecieResponse>
    suspend fun getStarships(page: Int): NetworkResponse<StarshipResponse>
    suspend fun getStarshipsSearch(filter : String, page : Int) : NetworkResponse<StarshipResponse>
    suspend fun getPlanets(page: Int): NetworkResponse<PlanetResponse>
    suspend fun getMovies() : NetworkResponse<MovieResponse>
    suspend fun getVehicles(page: Int): NetworkResponse<VehicleResponse>
}