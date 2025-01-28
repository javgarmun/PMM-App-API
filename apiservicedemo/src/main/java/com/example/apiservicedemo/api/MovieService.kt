package com.example.apiservicedemo.api

import com.example.apiservicedemo.API_TOKEN
import com.example.apiservicedemo.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $API_TOKEN"
    )

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language : String = "es-ES",
        @Query("page") page : Int = 1
    ): Call<MoviesResponse>
}