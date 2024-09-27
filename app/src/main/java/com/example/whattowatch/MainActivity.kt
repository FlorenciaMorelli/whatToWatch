package com.example.whattowatch

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.whattowatch.adapter.MovieAdapter
import com.example.whattowatch.databinding.ActivityMainBinding
import com.example.whattowatch.model.Movie
import com.example.whattowatch.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiKey = BuildConfig.TMDB_API_KEY

        // Configuración del RecyclerView
        binding.rvMovies.layoutManager = GridLayoutManager(this, 3)

        // Observamos los datos de películas en el ViewModel
        movieViewModel.movies.observe(this, Observer { movies ->
            binding.rvMovies.adapter = MovieAdapter(movies)
        })

        // Cargar las películas desde la API (usa tu API_KEY aquí)
        movieViewModel.getMovies(apiKey)

    }
}