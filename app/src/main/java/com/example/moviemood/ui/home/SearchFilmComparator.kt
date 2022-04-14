package com.example.moviemood.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.moviemood.model.Film

object SearchFilmComparator : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film) =
        oldItem.filmId != newItem.filmId

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean =
        oldItem.posterUrl == newItem.posterUrl &&
                oldItem.nameRu == newItem.nameRu
}