package com.android.desafiofinalstarwars.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("characters") val characters : List<String>,
    @SerializedName("created") val creationDate : String,
    @SerializedName("director") val director : String,
    @SerializedName("edited") val editedDate : String,
    @SerializedName("episode_id") val id : String?,
    @SerializedName("opening_crawl") val openingCrawl : String,
    @SerializedName("planets") val planets : List<String>,
    @SerializedName("producer") val productor : String,
    @SerializedName("release_date") val releaseDate : String,
    @SerializedName("species") val species : List<String>,
    @SerializedName("starships") val starships : List<String>,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url: String,
    @SerializedName("vehicles") val vehicles : List<String>
): Serializable