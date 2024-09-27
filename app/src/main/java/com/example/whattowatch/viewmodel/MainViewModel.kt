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

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    val apiKey = BuildConfig.TMDB_API_KEY

    fun fetchData(apiKey: String, onResult: (MovieResponse?) -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            Log.d("MainViewModel", "Antes de llamar a getPopular()")
            val response = RetrofitClient.api.getPopular(apiKey)
            if (response.isSuccessful){
                Log.d("MainViewModel", "Response de getPopular(): ${response.body()}")
                onResult(response.body())
            } else {
                onResult(null)
            }
        }
    }
}