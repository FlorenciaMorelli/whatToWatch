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
            // Cargar la imagen usando Picasso
            Picasso.with(itemView.context)
                .load(show.image)
                .into(binding.favoriteShowImage)
            // TO DO: placeholder y error

            root.setOnClickListener{ onItemClick(show) }
        }
    }
}

