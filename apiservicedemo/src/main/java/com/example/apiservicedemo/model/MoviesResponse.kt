package com.example.apiservicedemo.model

import com.squareup.moshi.Json

data class MoviesResponse(
    @Json(name = "page") val page: Int = 1,
    @Json(name = "total_pages") val total_pages: Int = 0,
    @Json(name = "total_results") val total_results: Int = 0,
    @Json(name = "results") val results: List<Movie>,
)
