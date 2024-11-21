package com.example.whattowatch.view.adapters

import android.R
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.whattowatch.databinding.ItemFavoriteShowBinding
import com.example.whattowatch.model.entities.FavoriteShow
import com.squareup.picasso.Picasso

class FavoriteShowViewHolder(
    private val binding: ItemFavoriteShowBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: FavoriteShow, onItemClick: (FavoriteShow) -> Unit) {
        Log.d("FavoriteShowViewHolder", "Creando la película: $show")
        binding.apply {
            binding.favoriteShowName.text = show.name
            //  Load image with Picasso
            val posterUrl = "https://image.tmdb.org/t/p/w500/${show.image}"
            Picasso.with(itemView.context)
                .load(posterUrl)
                .into(binding.favoriteShowImage)

            root.setOnClickListener{ onItemClick(show) }
        }
    }
}

