package com.example.whattowatch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattowatch.model.network.RetrofitClient
import com.example.whattowatch.model.entities.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> get() = _movieDetails

    fun getMovieDetails(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            val response = RetrofitClient.apiService.getMovieDetails(movieId, apiKey)
            if (response.isSuccessful) {
                _movieDetails.postValue(response.body())
            } else {
                Log.e("MovieViewModel", "Error fetching movie details: ${response.message()}")
            }
        }
    }
}
