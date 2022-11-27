package com.android.desafiofinalstarwars.retrofit.webclient.personagens

import com.android.desafiofinalstarwars.retrofit.webclient.personagens.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("people/")
    suspend fun getCharacters(
        @Query("page") page : Int
    ) : Response<CharacterResponse>

    @GET("people/?search=")
    suspend fun getCharactersSearch(
        @Query("search") filter: String,
        @Query("page") page : Int
    ) : Response<CharacterResponse>

    @GET("species/")
    suspend fun getSpecies(
        @Query("page") page : Int
    ) : Response<SpecieResponse>

    @GET("species/?search=")
    suspend fun getSpeciesSearch(
        @Query("search") filter: String,
        @Query("page") page: Int
    ) : Response<SpecieResponse>

    @GET("starships/")
    suspend fun getStarships(
        @Query("page") page : Int
    ) : Response<StarshipResponse>

    @GET("starships/?search=")
    suspend fun getStarshipsSearch(
        @Query("search") filter : String,
        @Query("page") page : Int
    ) : Response<StarshipResponse>

    @GET("vehicles/")
    suspend fun getVehicles(
        @Query("page") page : Int
    ) : Response<VehicleResponse>
    @GET("vehicles/?search=")
    suspend fun getVehiclesSearch(
        @Query("search") filter : String,
        @Query("page") page : Int
    ) : Response<VehicleResponse>

    @GET("planets/")
    suspend fun getPlanets(
        @Query("page") page : Int
    ) : Response<PlanetResponse>

    @GET("films/")
    suspend fun getMovies() : Response<MovieResponse>
}
