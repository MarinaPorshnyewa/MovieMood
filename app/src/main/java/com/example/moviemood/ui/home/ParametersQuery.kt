package com.example.moviemood.ui.home

import com.example.moviemood.utils.FILM_TYPE_FILTER
import com.example.moviemood.utils.NUM_VOTE_ORDER_FILTER

object ParametersQuery {
    var type: String = FILM_TYPE_FILTER
    var order: String = NUM_VOTE_ORDER_FILTER
    var ratingFrom: String = "6"
    var ratingTo: String = "10"
    var yearFrom: String = "2010"
    var yearTo: String = "2020"
    var country: String = "1"
    var genre: String = "2"
    var keyboard: String = "Marvel"
}