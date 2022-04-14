package com.example.moviemood.ui.info.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemGenreBinding
import com.example.moviemood.model.Genre

class GenreViewHolder(
    private val binding: ItemGenreBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(genre: Genre){
        binding.genreTextView.text = genre.genre
    }
}