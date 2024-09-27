package com.example.whattowatch.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.whattowatch.databinding.ItemMovieBinding
import com.example.whattowatch.model.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(movie: Movie){
        val image = movie.image
        Log.d("MoviewViewwHolder", "funcion bind(movie) recibió la película ${movie} y tiene el path ${image}")
        Picasso.with(itemView.context).load(image).into(binding.ivMovie)
    }

}