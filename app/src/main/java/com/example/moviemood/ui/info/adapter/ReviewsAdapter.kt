package com.example.moviemood.ui.info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemReviewBinding
import com.example.moviemood.model.DescriptionReview

class ReviewsAdapter(
    private val context: Context
): RecyclerView.Adapter<ReviewsViewHolder>() {

    private var list = arrayListOf<DescriptionReview>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(context))

        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setDataList(description: ArrayList<DescriptionReview>) {
        list = description
        notifyDataSetChanged()
    }
}