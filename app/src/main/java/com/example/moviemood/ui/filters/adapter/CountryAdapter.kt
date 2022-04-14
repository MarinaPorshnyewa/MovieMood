package com.example.moviemood.ui.filters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemCountryFiltersBinding
import com.example.moviemood.model.CountryFilters

class CountryAdapter(
    private val context: Context,
    val onItemClick: (item: CountryFilters) -> Unit
) :
    RecyclerView.Adapter<CountryViewHolder>() {

    var listHome: List<CountryFilters> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(ItemCountryFiltersBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(listHome[position])
        holder.itemView.setOnClickListener {
            onItemClick(listHome[position])
        }
    }

    override fun getItemCount(): Int = listHome.size

    fun setList(listData: List<CountryFilters>) {
        listHome = listData
        notifyDataSetChanged()
    }
}