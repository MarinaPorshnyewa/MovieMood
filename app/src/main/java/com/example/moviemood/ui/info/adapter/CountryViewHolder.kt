package com.example.moviemood.ui.info.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemCountryBinding
import com.example.moviemood.model.Country
import com.example.moviemood.model.Movie

class CountryViewHolder(
    private val binding: ItemCountryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Country){
        binding.nameCountryTextView.text = country.country
    }

}