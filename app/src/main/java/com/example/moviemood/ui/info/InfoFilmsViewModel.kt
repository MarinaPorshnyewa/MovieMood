package com.example.moviemood.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemood.model.*
import com.example.moviemood.repository.FilmsRepository
import com.example.moviemood.repository.SharedPreferencesRepositoriy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoFilmsViewModel(
    private val repository: FilmsRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepositoriy
) : ViewModel() {

    lateinit var loadInfoFilm: (str: Movie) -> Unit

    lateinit var loadReviesFilm: (str: ArrayList<DescriptionReview>) -> Unit

    lateinit var loadVideo: (url: ArrayList<VideoItem>) -> Unit

    lateinit var loadAnswer: () -> Unit

    lateinit var loadFacts: (str: ArrayList<FactItem>) -> Unit

    lateinit var loadImages: (images: ArrayList<ImagesItem>) -> Unit

    lateinit var loadSimilar: (similar: ArrayList<SimilarItem>) -> Unit

    val idData = MutableLiveData<Int>()

    val movie = MutableLiveData<Movie>()

    val list = MutableLiveData<ArrayList<Movie>>()

    fun saveFilm2(data: Movie) {
        val oldList = sharedPreferencesRepository.getListFilms()
        oldList.add(data)
        sharedPreferencesRepository.saveListFilms(oldList)
        list.value = oldList
    }
    fun saveFilmList(data: ArrayList<Movie>) {
        val oldList = sharedPreferencesRepository.getListFilms()
        //oldList.add(data)
        sharedPreferencesRepository.saveListFilms(oldList)
        list.value = oldList
    }

    fun getFavoriteFilm(){

    }


    fun getInfoFilm(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            val response = idData.value?.let { repository.getMovieById(id) }

            if (response != null) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        loadInfoFilm(it)
                    }

                } else {
                    response.errorBody()
                }
            }
        }
    }

    fun getReviewsFilm(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMovieReviewsById(id, 1)

            if (response.isSuccessful) {

                response.body()?.let {
                    loadReviesFilm(it.reviews)
                }

            } else {
                response.errorBody()
            }
        }
    }

    fun getVideoFilm(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getVideoFilmById(id)

            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.items[0].site == "YOUTUBE") {
                        loadVideo(it.items)
                    } else {
                        loadAnswer()
                    }
                }
            } else {
                response.errorBody()
            }
        }
    }

    fun getFactsFilm(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getFactsVilmById(id)
            if (response.isSuccessful) {
                response.body()?.let {

                    loadFacts(it.items)
                }
            } else {
                response.errorBody()
            }
        }
    }

    fun getImagesFilm(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getImagesFilmById(id)
            if (response.isSuccessful) {
                response.body()?.let {

                    loadImages(it.items)
                }
            } else {
                response.errorBody()
            }
        }
    }

    fun getSimilarFilm(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getSimilarFilmById(id)
            if (response.isSuccessful) {
                response.body()?.let {

                    loadSimilar(it.items)
                }
            } else {
                response.errorBody()
            }
        }
    }
}