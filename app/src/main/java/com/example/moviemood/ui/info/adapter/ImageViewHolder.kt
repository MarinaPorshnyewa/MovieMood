package com.example.moviemood.ui.info.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemood.databinding.ItemImageBinding
import com.example.moviemood.model.ImagesItem

class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: ImagesItem){
        Glide.with(context).load(image.imageUrl).into(binding.imageImageView)
    }
}