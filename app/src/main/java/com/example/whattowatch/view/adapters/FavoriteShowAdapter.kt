package com.example.whattowatch.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whattowatch.databinding.ItemFavoriteShowBinding
import com.example.whattowatch.model.entities.FavoriteShow

class FavoriteShowAdapter(
    private val onRemoveClick: (FavoriteShow) -> Unit
) : RecyclerView.Adapter<FavoriteShowViewHolder>() {

    private val favoriteShows = mutableListOf<FavoriteShow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteShowViewHolder {
        val binding = ItemFavoriteShowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteShowViewHolder(binding, onRemoveClick)
    }

    override fun onBindViewHolder(holder: FavoriteShowViewHolder, position: Int) {
        holder.bind(favoriteShows[position])
    }

    override fun getItemCount(): Int = favoriteShows.size

    // Método para actualizar la lista de favoritos
    fun updateList(newShows: List<FavoriteShow>) {
        favoriteShows.clear()
        favoriteShows.addAll(newShows)
        notifyDataSetChanged()
    }
}
