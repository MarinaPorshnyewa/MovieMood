package com.example.moviemood.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.moviemood.model.Movie

object FilmsComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem.kinopoiskId != newItem.kinopoiskId

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.posterUrl == newItem.posterUrl &&
                oldItem.nameOriginal == newItem.nameOriginal
}