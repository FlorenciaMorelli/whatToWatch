package com.example.whattowatch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattowatch.RetrofitClient
import com.example.whattowatch.model.Movie
import com.example.whattowatch.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val movies = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    fun getMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getPopularMovies(apiKey)
                movies.postValue(response.movies)
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
            }
        }
    }
}