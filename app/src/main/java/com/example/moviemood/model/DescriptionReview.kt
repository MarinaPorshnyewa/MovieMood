package com.example.moviemood.model

data class DescriptionReview(
    val reviewAutor: String,
    val reviewData: String,
    val reviewDescription: String,
    val reviewId: Int,
    val reviewTitle: String,
    val reviewType: String,
    val userNegativeRating: Int,
    val userPositiveRating: Int
)