package com.example.moviemood.ui.favorites

import com.example.moviemood.repository.SharedPreferencesRepositoriy
import com.example.moviemood.ui.base.BaseViewModelProvider
import javax.inject.Inject

class FavoriteFilmViewModelFactory @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepositoriy
) :
    BaseViewModelProvider<FavoriteFilmViewModel>(FavoriteFilmViewModel::class.java) {

    override fun createViewModel() = FavoriteFilmViewModel(sharedPreferencesRepository)
}