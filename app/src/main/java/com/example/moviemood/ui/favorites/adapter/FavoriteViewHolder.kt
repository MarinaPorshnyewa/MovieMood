package com.example.moviemood.ui.favorites.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemood.databinding.ItemFavoriteBinding
import com.example.moviemood.model.Favorite
import com.example.moviemood.model.Movie

class FavoriteViewHolder(
    private val binding: ItemFavoriteBinding,
    private val context : Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(favorite: Movie) {
        Glide.with(context).load(favorite.posterUrl).into(binding.imageFavoriteImageView)
        binding.nameFavoriteTextView.text = favorite.nameRu
        binding.favoriteFragmentTextViewGenre.text = favorite.genres[0].genre
    }
}