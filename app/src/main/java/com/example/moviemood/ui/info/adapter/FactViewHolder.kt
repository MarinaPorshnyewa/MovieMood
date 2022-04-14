package com.example.moviemood.ui.info.adapter

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemFactBinding
import com.example.moviemood.model.FactItem

class FactViewHolder(
    private val binding : ItemFactBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(fact: FactItem){
        binding.factTextView.text = Html.fromHtml(fact.text, Html.FROM_HTML_MODE_COMPACT)
    }
}