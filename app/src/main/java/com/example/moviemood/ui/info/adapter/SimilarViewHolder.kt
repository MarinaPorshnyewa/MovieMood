package com.example.moviemood.ui.info.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemood.databinding.ItemSimilarBinding
import com.example.moviemood.model.SimilarItem

class SimilarViewHolder(
    private val binding: ItemSimilarBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root){

    fun bind(similar: SimilarItem){
        Glide.with(context).load(similar.posterUrl).into(binding.imageSimilarImageView)
        binding.nameRuSimilarTextView.text = similar.nameRu
        binding.nameEnSimilarTextView.text = similar.nameEn
    }
}