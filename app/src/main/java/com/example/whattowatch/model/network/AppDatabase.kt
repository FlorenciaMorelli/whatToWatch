package com.example.whattowatch.model.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.whattowatch.model.entities.FavoriteShow

@Database(entities = [FavoriteShow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteShowDao(): FavoriteShowDao
}

object DatabaseInstance {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "favorite_shows_db"
            ).build()
        }
        return instance!!
    }
}