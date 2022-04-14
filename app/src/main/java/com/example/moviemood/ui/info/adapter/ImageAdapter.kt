package com.example.moviemood.ui.info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemImageBinding
import com.example.moviemood.model.FactItem
import com.example.moviemood.model.ImagesItem

class ImageAdapter(
    private val context: Context
) : RecyclerView.Adapter<ImageViewHolder>() {

    private var list = arrayListOf<ImagesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(context))
        return ImageViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setDataList(image: ArrayList<ImagesItem>) {
        list = image
        notifyDataSetChanged()
    }
}