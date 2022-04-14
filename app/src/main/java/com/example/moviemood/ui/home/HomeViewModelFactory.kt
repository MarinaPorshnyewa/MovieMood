package com.example.moviemood.ui.home

import com.example.moviemood.paging.FilmsDataSource
import com.example.moviemood.paging.FilmsSearchDataSource
import com.example.moviemood.ui.base.BaseViewModelProvider
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val filmsDataSource: FilmsDataSource,
    private val searchDataSource: FilmsSearchDataSource
) : BaseViewModelProvider<HomeViewModel>(HomeViewModel::class.java) {

    override fun createViewModel(): HomeViewModel {
        return HomeViewModel(filmsDataSource, searchDataSource)
    }
}