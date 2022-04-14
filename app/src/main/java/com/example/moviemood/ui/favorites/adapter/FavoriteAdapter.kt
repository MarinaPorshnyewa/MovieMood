package com.example.moviemood.ui.favorites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemFavoriteBinding
import com.example.moviemood.model.Favorite
import com.example.moviemood.model.Movie

class FavoriteAdapter(
    private val context: Context,
    val onItemClick: (item: Movie) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>(){

    private var list = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(context))
        return FavoriteViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClick(list[position])
        }
    }

    override fun getItemCount() = list.size

    fun setDataList(movie: ArrayList<Movie>){
        list = movie
        notifyDataSetChanged()
    }
}