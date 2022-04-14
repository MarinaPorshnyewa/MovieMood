package com.example.moviemood.ui.premieres

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemood.model.Filters
import com.example.moviemood.model.Premieres
import com.example.moviemood.repository.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PremieresViewModel (
    private val repository: FilmsRepository
) : ViewModel() {

    val listPremieres= MutableLiveData<Premieres>()

    fun getPremieres() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPremieres("2022","APRIL")
            if (response.isSuccessful) {
                listPremieres.postValue(response.body())
            }
        }
    }
}