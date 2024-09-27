package com.example.whattowatch

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter
    private val movies = listOf<Movie>()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        val apiKey = BuildConfig.TMDB_API_KEY

        viewModel.fetchData(apiKey){ movie ->    //  Función lambda
            movie?.let { //  El let hace que trabajemos sobre datos que NO SON NULOS
                lifecycleScope.launch {
                    initRecycler()
                }
            }

        }



        viewModel.movies.observe(this){
            adapter.movies = it
            adapter.notifyDataSetChanged()
            Log.d("MainActivity", "Cambió la info a: ${adapter}")
        }
    }

    private fun initRecycler(){
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.layoutManager = layoutManager
        adapter = MovieAdapter(movies)
        binding.rvMovies.adapter = adapter
        Log.d("MainActivity", "Cargando recyclerview con: ${adapter}")
    }
}