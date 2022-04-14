package com.example.moviemood.network

import com.example.moviemood.model.Films
import com.example.moviemood.model.Filters
import com.example.moviemood.model.Movie
import com.example.moviemood.model.SearchFilms
import com.example.moviemood.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int
    ): Response<Movie>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films")
    suspend fun getAllFilms(
        @Query("countries") countries: String,
        @Query("genres") genres: String,
        @Query("order") order: String,
        @Query("type") type: String,
        @Query("ratingFrom") ratingFrom: String,
        @Query("ratingTo") ratingTo: String,
        @Query("yearFrom") yearFrom: String,
        @Query("yearTo") yearTo: String,
        @Query("page") page: String
    ): Response<Films>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.1/films/search-by-keyword")
    suspend fun getFilmsSearch(
        @Query("keyword") keyword: String,
        @Query("page") page: String
    ): Response<SearchFilms>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/filters")
    suspend fun getFilters(): Response<Filters>

    @GET("/api/v1/reviews")
    suspend fun getReviewsById(
        @Query("filmId") id: Int,
        @Query("page") str: Int
    ): Response<Reviews>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/{id}/videos")
    suspend fun getVideoById(
        @Path("id") id: Int
    ): Response<Video>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/{id}/facts")
    suspend fun getFactsById(
        @Path("id") id: Int
    ): Response<Fact>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/{id}/images")
    suspend fun getImagesById(
        @Path("id") id: Int
    ): Response<Images>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/{id}/similars")
    suspend fun getSimilarById(
        @Path("id") id: Int
    ): Response<Similar>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun get250TopFilms(
        @Query("page") page: String? = null
    ): Response<TopFilmsData>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun get100TopFilms(
        @Query("page") page: String? = null
    ): Response<TopFilmsData>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/top?type=TOP_AWAIT_FILMS")
    suspend fun getAwaitTopFilms(
        @Query("page") page: String? = null
    ): Response<TopFilmsData>

    @Headers("X-API-KEY: 72018ceb-210e-424a-b0f9-865f1a7b26e5", "Content-Type: application/json")
    @GET("/api/v2.2/films/premieres")
    suspend fun getPremieres(
        @Query("year") year: String,
        @Query("month") month: String
    ): Response<Premieres>
}