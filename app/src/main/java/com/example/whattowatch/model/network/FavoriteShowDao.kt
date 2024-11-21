package com.example.whattowatch.model.network

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whattowatch.model.entities.FavoriteShow

@Dao
interface FavoriteShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(show: FavoriteShow)

    @Delete
    suspend fun delete(show: FavoriteShow)
    @Query("SELECT * FROM favorite_shows")
    fun getAllFavorites(): LiveData<List<FavoriteShow>>
}