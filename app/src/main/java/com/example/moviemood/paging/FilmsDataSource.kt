package com.example.moviemood.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviemood.model.Movie
import com.example.moviemood.repository.FilmsRepository
import com.example.moviemood.ui.home.ParametersQuery
import javax.inject.Inject

class FilmsDataSource @Inject constructor(
    private val repository: FilmsRepository
) : PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 1
            val country = ParametersQuery.country
            val genre = ParametersQuery.genre
            val order = ParametersQuery.order
            val type = ParametersQuery.type
            val ratingFrom = ParametersQuery.ratingFrom
            val ratingTo = ParametersQuery.ratingTo
            val yearFrom = ParametersQuery.yearFrom
            val yearTo = ParametersQuery.yearTo
            val response = repository.getAllFilms(
                country,
                genre,
                order,
                type,
                ratingFrom,
                ratingTo,
                yearFrom,
                yearTo,
                nextPageNumber.toString()
            )
            LoadResult.Page(
                data = response.body()?.items ?: arrayListOf(),
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}