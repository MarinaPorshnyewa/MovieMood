package com.example.moviemood.model

data class Films(
    val total: Int,
    val totalPages: Int,
    val items: List<Movie>
)