package com.example.moviemood.ui.filters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemGenreFiltresBinding
import com.example.moviemood.model.GenreFilters

class GenreAdapter(
    private val context: Context,
    val onItemClick: (item: GenreFilters) -> Unit
) : RecyclerView.Adapter<GenreViewHolder>() {

    var listHome: List<GenreFilters> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(ItemGenreFiltresBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(listHome[position])
        holder.itemView.setOnClickListener {
            onItemClick(listHome[position])
        }
    }

    override fun getItemCount(): Int = listHome.size

    fun setList(listData: List<GenreFilters>) {
        listHome = listData
        notifyDataSetChanged()
    }

}