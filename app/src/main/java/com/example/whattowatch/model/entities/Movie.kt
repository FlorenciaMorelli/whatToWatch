package com.example.whattowatch.model.entities

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("vote_average")
    val score: String,
    @SerializedName("vote_count")
    val votes: String
)
