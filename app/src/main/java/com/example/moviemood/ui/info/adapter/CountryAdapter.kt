package com.example.moviemood.ui.info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemCountryBinding
import com.example.moviemood.model.Country

class CountryAdapter(
    private val context: Context
) : RecyclerView.Adapter<CountryViewHolder>(){

    private var list = arrayListOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(context))
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setDataList(country: ArrayList<Country>){
        list = country
        notifyDataSetChanged()
    }
}