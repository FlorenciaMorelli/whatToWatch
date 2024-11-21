package com.example.whattowatch.model.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.whattowatch.model.entities.FavoriteShow

@Database(entities = [FavoriteShow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteShowDao(): FavoriteShowDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "what_to_watch_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        //  Close DB when it's not needed anymore
        fun destroyInstance() {
            INSTANCE = null
        }
    }
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