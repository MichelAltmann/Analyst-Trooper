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

    @GET("starships/")
    suspend fun getStarships(
        @Query("page") page : Int
    ) : Response<StarshipResponse>

    @GET("planets/")
    suspend fun getPlanets() : Response<PlanetResponse>

    @GET("species/")
    suspend fun getSpecies(
        @Query("page") page : Int
    ) : Response<SpecieResponse>

    @GET("films/")
    suspend fun getMovies() : Response<MovieResponse>

    @GET("vehicles/")
    suspend fun getVehicles(
        @Query("page") page : Int
    ) : Response<VehicleResponse>
}