package com.example.whattowatch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whattowatch.adapter.MovieAdapter
import com.example.whattowatch.databinding.ActivityMainBinding
import com.example.whattowatch.model.Movie
import com.example.whattowatch.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MainViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Extracting API Key from apikeys.properties
        val apiKey = BuildConfig.TMDB_API_KEY

        //  RecyclerView configuration and adapter setup for movies
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)

        //  Observer for the list of movies in the ViewModel and update the adapter accordingly when the data changes
        movieViewModel.movies.observe(this, Observer { movies ->
            movieAdapter = MovieAdapter(movies) { movie ->
                val intent = Intent(this, MovieDetail::class.java).apply {
                    putExtra("MOVIE_ID", movie.id)  //  Movie ID is passed to the MovieDetail activity
                }
                startActivity(intent)
            }
            binding.rvMovies.adapter = movieAdapter
        })

        // Loading movies using the API key and the ViewModel to fetch the data and update the UI.
        movieViewModel.getMovies(apiKey)

        //  Listening query on SearchView
        binding.svMovies.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    movieViewModel.searchMovies(apiKey, it)  // Calls the searchMovies function in the ViewModel
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //  Listening as query changes
                newText?.let {
                    if (it.isNotEmpty()) {
                        movieViewModel.searchMovies(apiKey, it)
                    }
                }
                return true
            }
        })
    }
}