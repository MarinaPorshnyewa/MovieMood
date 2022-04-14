package com.example.moviemood.ui.filters

import com.example.moviemood.repository.FilmsRepository
import com.example.moviemood.ui.base.BaseViewModelProvider
import javax.inject.Inject

class FiltersViewModelFactory @Inject constructor(
    private val repository: FilmsRepository
) : BaseViewModelProvider<FiltersViewModel>(FiltersViewModel::class.java) {

    override fun createViewModel(): FiltersViewModel {
        return FiltersViewModel(repository)
    }
}