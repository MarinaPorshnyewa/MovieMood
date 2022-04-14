package com.example.moviemood.ui.premieres

import com.example.moviemood.repository.FilmsRepository
import com.example.moviemood.ui.base.BaseViewModelProvider
import com.example.moviemood.ui.filters.FiltersViewModel
import javax.inject.Inject

class PremieresViewModelFactory @Inject constructor(
    private val repository: FilmsRepository
) : BaseViewModelProvider<PremieresViewModel>(PremieresViewModel::class.java) {

    override fun createViewModel(): PremieresViewModel {
        return PremieresViewModel(repository)
    }
}