package com.example.moviemood.repository

import com.example.moviemood.di.NetworkManagerModule
import com.example.moviemood.model.TopFilmsData
import retrofit2.Response

class FilmRepositoryWithoutDaggerFixIt {
    suspend fun get250TopFilms(page: String?): Response<TopFilmsData> {
        return NetworkManagerModule().provideFilmsRequestsApi().get250TopFilms(page)
    }

    suspend fun get100TopFilms(page: String?): Response<TopFilmsData> {
        return NetworkManagerModule().provideFilmsRequestsApi().get100TopFilms(page)
    }

    suspend fun getAwaitTopFilms(page: String?): Response<TopFilmsData> {
        return NetworkManagerModule().provideFilmsRequestsApi().getAwaitTopFilms(page)
    }
}