package com.example.moviemood.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviemood.model.Film
import com.example.moviemood.model.TopFilmsData
import com.example.moviemood.repository.FilmRepositoryWithoutDaggerFixIt
import retrofit2.Response

class KinopoiskDataSource(private val countOfFragment: Int) : PagingSource<Int, Film>() {
    private var totalPages = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response: Response<TopFilmsData> = when (countOfFragment) {
                1 -> FilmRepositoryWithoutDaggerFixIt().get250TopFilms(nextPageNumber.toString())
                2 -> FilmRepositoryWithoutDaggerFixIt().get100TopFilms(nextPageNumber.toString())
                3 -> FilmRepositoryWithoutDaggerFixIt().getAwaitTopFilms(nextPageNumber.toString())
                else -> FilmRepositoryWithoutDaggerFixIt().get250TopFilms(nextPageNumber.toString())
            }
            //val response = FilmsRepository().get250TopFilms(nextPageNumber.toString())
            totalPages = response.body()?.pagesCount ?: 0
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