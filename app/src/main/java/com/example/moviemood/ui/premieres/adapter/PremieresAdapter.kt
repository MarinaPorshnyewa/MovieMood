package com.example.moviemood.ui.premieres.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemPremiersFilmBinding
import com.example.moviemood.model.FilmPremieres

class PremieresAdapter(
    private val context: Context,
    val onItemClick: (item: FilmPremieres) -> Unit
) :
    RecyclerView.Adapter<PremieresViewHolder>() {

    var listHome: List<FilmPremieres> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremieresViewHolder {
        return PremieresViewHolder(ItemPremiersFilmBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: PremieresViewHolder, position: Int) {
        holder.bind(listHome[position])
        holder.itemView.setOnClickListener {
            onItemClick(listHome[position])
        }
    }

    override fun getItemCount(): Int = listHome.size

    fun setList(listData: List<FilmPremieres>) {
        listHome = listData
        notifyDataSetChanged()
    }
}