package com.example.moviemood.ui.info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemFactBinding
import com.example.moviemood.model.DescriptionReview
import com.example.moviemood.model.FactItem

class FactAdapter(
    private val context: Context
) : RecyclerView.Adapter<FactViewHolder>(){

    private var list = arrayListOf<FactItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding = ItemFactBinding.inflate(LayoutInflater.from(context))
        return FactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setDataList(fact: ArrayList<FactItem>) {
        list = fact
        notifyDataSetChanged()
    }
}