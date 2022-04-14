package com.example.moviemood.model

data class SimilarItem (
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val nameOriginal: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val relationType: String
)