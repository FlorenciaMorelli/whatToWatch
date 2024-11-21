package com.example.whattowatch.model.repositories

import com.example.whattowatch.model.entities.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movies: List<Movie>
)
