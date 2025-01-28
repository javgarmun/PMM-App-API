package com.example.apiservicedemo.model

data class MoviesResponse(
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
    val results: List<Movie>,
)
