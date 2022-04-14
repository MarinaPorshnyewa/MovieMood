package com.example.moviemood.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviemood.model.Film
import com.example.moviemood.model.Movie
import com.example.moviemood.repository.FilmsRepository
import com.example.moviemood.ui.home.ParametersQuery
import javax.inject.Inject

class FilmsSearchDataSource @Inject constructor(
    private val repository: FilmsRepository
): PagingSource<Int, Film>(){


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPageNumber = params.key ?: 1
            val keyboard = ParametersQuery.keyboard
            val response = repository.getFilmsSearch(keyboard, nextPageNumber.toString())
            LoadResult.Page(
                data = response.body()?.films ?: arrayListOf(),
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}