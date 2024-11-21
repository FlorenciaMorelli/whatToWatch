package com.example.whattowatch.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.whattowatch.databinding.ItemMovieBinding
import com.example.whattowatch.model.entities.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(movie: Movie) {
        // Set the movie title
        binding.tvMovie.text = movie.title

        // Load the movie poster using Picasso
        val posterUrl = "https://image.tmdb.org/t/p/w500/${movie.image}"
        Picasso.with(itemView.context)
            .load(posterUrl)
            .into(binding.ivMovie)
    }

}