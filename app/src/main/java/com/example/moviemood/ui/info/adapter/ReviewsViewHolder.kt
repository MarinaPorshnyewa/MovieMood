package com.example.moviemood.ui.info.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.ItemReviewBinding
import com.example.moviemood.model.DescriptionReview

class ReviewsViewHolder(
    private val binding: ItemReviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(review: DescriptionReview) {

        binding.nameReviewTextView.text = review.reviewAutor
        binding.dateReviewTextView.text = review.reviewData
        binding.descriptionTextView.text = review.reviewDescription
    }
}