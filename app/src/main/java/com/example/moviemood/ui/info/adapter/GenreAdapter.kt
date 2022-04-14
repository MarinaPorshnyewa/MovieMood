package com.example.moviemood.ui.info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemGenreBinding
import com.example.moviemood.model.Genre

class GenreAdapter(
    private val context: Context
) : RecyclerView.Adapter<GenreViewHolder>(){

    private var list = arrayListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(context))
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun getDataList(genre: ArrayList<Genre>){
        list = genre
        notifyDataSetChanged()
    }
}