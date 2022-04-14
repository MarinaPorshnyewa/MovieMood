package by.evgen.apitest.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviemood.model.Film

object KinopoiskFilmComparator : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return (oldItem.equals(newItem.countries) &&
                oldItem.genres == newItem.genres &&
                oldItem.posterUrl == newItem.posterUrl)

    }
}