package com.thenotesgiver.tmdbapimvvm.data.model

data class MovieModel(
    val page: Int,
    val results: MutableList<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)