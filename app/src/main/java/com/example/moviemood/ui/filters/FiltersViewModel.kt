package com.example.moviemood.ui.filters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemood.model.Filters
import com.example.moviemood.repository.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FiltersViewModel(
    private val repository: FilmsRepository
) : ViewModel() {

    val listFilters = MutableLiveData<Filters>()

    fun getFilters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getFilters()
            if (response.isSuccessful) {
                listFilters.postValue(response.body())
            }
        }
    }
}