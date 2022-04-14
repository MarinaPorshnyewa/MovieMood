package com.example.moviemood.ui.filters.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemCountryFiltersBinding
import com.example.moviemood.model.CountryFilters

class CountryViewHolder(
    private val binding: ItemCountryFiltersBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CountryFilters) {
        if (item.country != "") {
            binding.checkbox.text = item.country
        }
    }
}