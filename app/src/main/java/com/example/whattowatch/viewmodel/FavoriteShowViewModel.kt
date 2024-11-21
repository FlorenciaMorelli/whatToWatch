package com.example.whattowatch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.whattowatch.model.entities.FavoriteShow
import com.example.whattowatch.model.network.DatabaseInstance
import kotlinx.coroutines.launch

class FavoriteShowViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteDao = DatabaseInstance.getDatabase(application).favoriteShowDao()

    val favoriteShows: LiveData<List<FavoriteShow>> = favoriteDao.getAllFavorites()

    fun addFavorite(show: FavoriteShow) {
        viewModelScope.launch {
            favoriteDao.insert(show)
        }
    }

    fun removeFavorite(show: FavoriteShow) {
        viewModelScope.launch {
            favoriteDao.delete(show)
        }
    }
}