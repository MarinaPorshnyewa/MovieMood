package com.example.moviemood.ui.filters.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemGenreFiltresBinding
import com.example.moviemood.model.GenreFilters

class GenreViewHolder(
    private val binding: ItemGenreFiltresBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GenreFilters) {
        if (item.genre != "") {
            binding.checkbox.text = item.genre
        }
    }
}