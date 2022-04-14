package com.example.moviemood.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemood.repository.FilmsRepository
import com.example.moviemood.repository.SharedPreferencesRepositoriy
import com.example.moviemood.ui.base.BaseViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class InfoFilmsViewModelFactory @Inject constructor(
    private val repository: FilmsRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepositoriy
) : BaseViewModelProvider<InfoFilmsViewModel>(InfoFilmsViewModel::class.java) {

    override fun createViewModel(): InfoFilmsViewModel {
        return InfoFilmsViewModel(
            repository,
            sharedPreferencesRepository
        )
    }
}