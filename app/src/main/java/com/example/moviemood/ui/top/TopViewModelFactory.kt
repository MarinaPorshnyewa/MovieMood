package com.example.moviemood.ui.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TopViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopViewModel::class.java)) {
            return TopViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}