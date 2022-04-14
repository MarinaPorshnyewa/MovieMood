package com.example.moviemood.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemFilmsBinding
import com.example.moviemood.model.Movie
import com.example.moviemood.utils.loadUrl

class FilmsViewHolder(
    private val binding: ItemFilmsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        item.posterUrl.let {
            binding.homeFragmentImageViewPoster.loadUrl(it)
        }
        binding.homeFragmentTextViewNameMovie.text = item.nameRu
        if (item.nameRu == null) {
            binding.homeFragmentTextViewNameMovie.text = item.nameOriginal
        }
        binding.homeFragmentTextViewGenre.text = item.genres[0].genre
    }
}