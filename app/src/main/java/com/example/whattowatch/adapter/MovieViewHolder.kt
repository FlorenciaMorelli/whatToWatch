package com.example.whattowatch.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.whattowatch.databinding.ItemMovieBinding
import com.example.whattowatch.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(movie: Movie) {
        // Set the movie title
        binding.tvMovie.text = movie.title

        // Load the movie poster using Picasso
        val posterUrl = "https://image.tmdb.org/t/p/w500/${movie.image}"
        Picasso.with(itemView.context)
            .load(posterUrl)
            .into(binding.ivMovie)           // ImageView donde se carga el poster

        // .placeholder(R.drawable.placeholder)  // Opcional, imagen mientras se carga
        //            .error(R.drawable.error_image)        // Opcional, imagen si falla
    }

}