package com.example.moviemood.model

data class Reviews(
    val filmId: Int,
    val page: Int,
    val pagesCount: Int,
    val reviewAllCount: Int,
    val reviewAllPositiveRatio: String,
    val reviewNegativeCount: Int,
    val reviewNeutralCount: Int,
    val reviewPositiveCount: Int,
    val reviews: ArrayList<DescriptionReview>
)