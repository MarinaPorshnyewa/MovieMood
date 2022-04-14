package by.evgen.apitest.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemood.databinding.ItemTopBinding
import com.example.moviemood.model.Film

class KinopoiskViewHolder(
    val binding: ItemTopBinding,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Film) {
        binding.titleTextView.text = item.nameRu
        binding.genreTextView.text = getStringGenre(item)
        binding.posterImageView.loadUrl(item.posterUrlPreview)
    }

    fun ImageView.loadUrl(url: String) {
        Glide.with(context).load(url).into(this)
    }

    private fun getStringGenre(item: Film): String {
        val sb = StringBuilder()
        for (genre in item.genres) {
            sb.append(genre.genre)
            sb.append(", ")
        }
        return sb.toString()
    }
}