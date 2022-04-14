package com.example.moviemood.model

data class Filters(
    val countries: List<CountryFilters>,
    val genres: List<GenreFilters>
)