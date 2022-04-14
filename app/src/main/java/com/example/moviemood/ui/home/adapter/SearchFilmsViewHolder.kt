package com.example.moviemood.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemFilmsBinding
import com.example.moviemood.model.Film
import com.example.moviemood.utils.loadUrl

class SearchFilmsViewHolder(
    private val binding: ItemFilmsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Film) {
        if(item.posterUrl != "") {
            item.posterUrl.let {
                binding.homeFragmentImageViewPoster.loadUrl(it)
            }
            binding.homeFragmentTextViewNameMovie.text = item.nameRu
            if (item.nameRu == null) {
                binding.homeFragmentTextViewNameMovie.text = item.nameEn
            }
            binding.homeFragmentTextViewGenre.text = item.genres[0].genre
        }
    }
}