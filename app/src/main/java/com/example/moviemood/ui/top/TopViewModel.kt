package com.example.moviemood.ui.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviemood.model.Film
import com.example.moviemood.paging.KinopoiskDataSource

class TopViewModel : ViewModel() {
    private var countOfFragment: Int = 1

    val result = Pager<Int, Film>(PagingConfig(20), 0) {
        KinopoiskDataSource(countOfFragment)
    }.flow.cachedIn(viewModelScope)

    fun setCountOfFragment(countOfFragment: Int) {
        this.countOfFragment = countOfFragment
    }
}