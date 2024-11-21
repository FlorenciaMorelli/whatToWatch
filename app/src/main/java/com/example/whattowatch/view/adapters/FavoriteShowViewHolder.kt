package com.example.whattowatch.view.adapters

import android.R
import androidx.recyclerview.widget.RecyclerView
import com.example.whattowatch.databinding.ItemFavoriteShowBinding
import com.example.whattowatch.model.entities.FavoriteShow
import com.squareup.picasso.Picasso

class FavoriteShowViewHolder(
    private val binding: ItemFavoriteShowBinding,
    private val onRemoveClick: (FavoriteShow) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: FavoriteShow) {
        binding.favoriteShowName.text = show.name

        // Cargar la imagen usando Picasso
        Picasso.with(itemView.context)
            .load(show.image)
            .into(binding.favoriteShowImage)
        // TO DO: placeholder y error

        // Configurar botón para eliminar el show favorito
        binding.removeFavoriteButton.setOnClickListener {
            onRemoveClick(show)
        }
    }
}

