package com.example.apiservicedemo

import com.example.apiservicedemo.api.MovieService
import com.example.apiservicedemo.model.MoviesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val API_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNzhkMGVmYTZmNDI0ZmMwNDdmMDEwMTdlODFjOGJjOSIsIm5iZiI6MTczODA3Njc2MC41MTcsInN1YiI6IjY3OThmMjU4OWEzMGE4NWIyNzI0M2IzYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pPFIKX_Qk5bhjKCFRx9gkQoEpiIrojQDrNL2f7WQTh8"

fun main() {
    //println(getMoviesJSON())
    //ejemplo2()
    ejemploRetrofit()
}

fun getMoviesJSON() : String? {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://api.themoviedb.org/3/movie/now_playing?language=es-ES&page=1")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer $API_TOKEN")
        .build()

    val call = client.newCall(request)

    val response = call.execute()

    val json = response.body?.string()

    return json
}

fun ejemplo2() {
    val json = getMoviesJSON()

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val jsonAdapter = moshi.adapter(MoviesResponse::class.java)

    val moviesResponse = jsonAdapter.fromJson(json.toString())

    printMoviesTitles(moviesResponse)
}

private fun printMoviesTitles(moviesResponse: MoviesResponse?) {
    moviesResponse?.results?.forEach {
        println(it.title)
    }
}

fun ejemploRetrofit() {
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val movieService = retrofit.create(MovieService::class.java)

    val call = movieService.getNowPlayingMovies()

    val response = call.execute()

    val moviesResponse = response.body()

    printMoviesTitles(moviesResponse)
}