package com.example.moviemood.repository

import com.example.moviemood.di.NetworkManagerModule
import com.example.moviemood.model.Country
import com.example.moviemood.model.TopFilmsData
import com.example.moviemood.network.ApiService
import com.example.moviemood.utils.NUM_VOTE_ORDER_FILTER
import retrofit2.Response
import javax.inject.Inject

class FilmsRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getAllFilms(
        country: String,
        genre: String,
        order: String,
        type: String,
        ratingFrom: String,
        ratingTo: String,
        yearFrom: String,
        yearTo: String,
        page: String
    ) =
        apiService.getAllFilms(
            country, genre, order, type, ratingFrom, ratingTo, yearFrom, yearTo, page
        )

    suspend fun getFilmsSearch(keyboard: String, page: String) =
        apiService.getFilmsSearch(keyboard, page)

    suspend fun getFilters() =
        apiService.getFilters()

    suspend fun getMovieById(id: Int) = apiService.getMovieById(id)

    suspend fun getMovieReviewsById(filmId: Int, str: Int) = apiService.getReviewsById(filmId, str)

    suspend fun getVideoFilmById(id: Int) = apiService.getVideoById(id)

    suspend fun getFactsVilmById(id: Int) = apiService.getFactsById(id)

    suspend fun getImagesFilmById(id: Int) = apiService.getImagesById(id)

    suspend fun getSimilarFilmById(id: Int) = apiService.getSimilarById(id)

    suspend fun getPremieres(year: String, month: String) = apiService.getPremieres(year, month)
}

