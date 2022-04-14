package com.example.moviemood.ui.premieres.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemPremiersFilmBinding
import com.example.moviemood.model.FilmPremieres
import com.example.moviemood.utils.loadUrl

class PremieresViewHolder(
    private val binding: ItemPremiersFilmBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FilmPremieres) {
        item.posterUrl.let {
            binding.homeFragmentImageViewPoster.loadUrl(it)
        }
        if(item.nameRu != null) {
            binding.homeFragmentTextViewNameMovie.text = item.nameRu
        }
        if (item.nameRu == null && item.nameEn != null ) {
            binding.homeFragmentTextViewNameMovie.text = item.nameEn
        }
        binding.homeFragmentTextViewGenre.text = item.premiereRu
    }
}
