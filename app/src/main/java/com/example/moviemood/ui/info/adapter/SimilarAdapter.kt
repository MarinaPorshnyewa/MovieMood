package com.example.moviemood.ui.info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemSimilarBinding
import com.example.moviemood.model.SimilarItem

class SimilarAdapter(
    private val context: Context,
    val onItemClick: (item: SimilarItem) -> Unit
) :RecyclerView.Adapter<SimilarViewHolder>() {

    private var list = arrayListOf<SimilarItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val binding = ItemSimilarBinding.inflate(LayoutInflater.from(context))
        return SimilarViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            onItemClick(list[position])
        }
    }

    override fun getItemCount() = list.size

    fun setDataList(similar : ArrayList<SimilarItem>){
        list = similar
        notifyDataSetChanged()
    }
}