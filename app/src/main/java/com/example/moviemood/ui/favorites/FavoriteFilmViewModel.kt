package com.example.moviemood.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemood.model.Movie
import com.example.moviemood.repository.SharedPreferencesRepositoriy

class FavoriteFilmViewModel(
    private val sharedPreferencesRepository: SharedPreferencesRepositoriy
) : ViewModel()  {

    val list = MutableLiveData<ArrayList<Movie>>()

    fun saveHero(data: Movie) {
        val oldList = sharedPreferencesRepository.getListFilms()
        oldList.add(data)
        sharedPreferencesRepository.saveListFilms(oldList)
        list.value = oldList
    }


}