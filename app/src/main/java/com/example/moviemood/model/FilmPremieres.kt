package com.example.moviemood.model

data class FilmPremieres(
    val countries: List<Country>,
    val duration: Any,
    val genres: List<Genre>,
    val kinopoiskId: Int,
    val nameEn: String?,
    val nameRu: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val premiereRu: String,
    val year: Int
)