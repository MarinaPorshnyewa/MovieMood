package com.example.moviemood.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviemood.paging.FilmsDataSource
import com.example.moviemood.paging.FilmsSearchDataSource

class HomeViewModel(
    private val filmsDataSource: FilmsDataSource,
    private val filmsSearchDataSource: FilmsSearchDataSource
) : ViewModel() {

    val result = Pager(PagingConfig(20), 1) {
        filmsDataSource
    }.flow.cachedIn(viewModelScope)


    val resultTwo = Pager(PagingConfig(20), 1) {
        filmsSearchDataSource
    }.flow.cachedIn(viewModelScope)
}