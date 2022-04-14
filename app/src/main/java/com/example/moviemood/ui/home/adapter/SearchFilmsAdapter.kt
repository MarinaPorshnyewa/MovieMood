package com.example.moviemood.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.moviemood.databinding.ItemFilmsBinding
import com.example.moviemood.model.Film
import com.example.moviemood.model.Movie
import com.example.moviemood.ui.home.SearchFilmComparator


class SearchFilmsAdapter(
    private val context: Context,
    val onItemClick: (item: Film) -> Unit
) :
    PagingDataAdapter<Film, SearchFilmsViewHolder>(
        SearchFilmComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFilmsViewHolder {
        return SearchFilmsViewHolder(ItemFilmsBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: SearchFilmsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onItemClick(it1) }
        }
    }
}