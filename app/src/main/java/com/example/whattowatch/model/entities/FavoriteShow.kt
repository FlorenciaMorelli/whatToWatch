package com.example.whattowatch.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_shows")
data class FavoriteShow(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val summary: String
)