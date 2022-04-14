package com.example.moviemood.ui.top.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import by.evgen.apitest.adapter.KinopoiskFilmComparator
import by.evgen.apitest.adapter.KinopoiskViewHolder
import com.example.moviemood.databinding.ItemTopBinding
import com.example.moviemood.model.Film

class KinopoiskDataSourceAdapter(
    private val context: Context,
    private val onItemClick: (item: Film) -> Unit
) :
    PagingDataAdapter<Film, KinopoiskViewHolder>(KinopoiskFilmComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinopoiskViewHolder {
        return KinopoiskViewHolder(ItemTopBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: KinopoiskViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.binding.root.setOnClickListener {
            getItem(position)?.let { it1 -> onItemClick(it1) }
        }
    }
}