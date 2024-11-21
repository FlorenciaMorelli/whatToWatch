package com.example.whattowatch.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whattowatch.R
import com.example.whattowatch.model.entities.Movie

class MovieAdapter(
    var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(layoutInflater)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            Log.d("MovieAdapter", "Item $position clicked")
            onItemClick(item)
        }
    }
}