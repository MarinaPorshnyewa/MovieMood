package com.example.moviemood.model

data class SearchFilms(
    val keyword: String,
    val pagesCount: Int,
    val films: List<Film>?,
    val searchFilmsCountResult: Int
)