package com.example.whattowatch

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.whattowatch.databinding.ActivityMovieDetailBinding
import com.example.whattowatch.model.Movie
import com.example.whattowatch.viewmodel.MainViewModel
import com.example.whattowatch.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Extracting API Key from apikeys.properties
        val apiKey = BuildConfig.TMDB_API_KEY

        val movieId = intent.getIntExtra("MOVIE_ID", -1)

        // Verify that the ID is valid and get the details
        if (movieId != -1) {
            movieDetailViewModel.getMovieDetails(movieId, apiKey)
        }

        movieDetailViewModel.movieDetails.observe(this, Observer { movie ->
            movie?.let {
                displayMovieDetails(it)
            }
        })
    }

    private fun displayMovieDetails(movie: Movie) {
        //  Apply the data to the views
        binding.tvDetailTitle.text = movie.title
        binding.tvDetailOverview.text = movie.description

        //  Obtain the year from the date and set it to the TextView
        val movieDate = movie.date
        val simpleDateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val year = simpleDateFormat.parse(movieDate)?.year?.plus(1900)
        binding.tvDetailDate.text = year?.toString() ?: ""

        //  Set the rating to the TextView
        binding.tvDetailRating.text = movie.score

        // Construct the full image URL
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.image}"

        //  Load the image using Picasso
        Picasso.with(binding.root.context)
            .load(imageUrl)
            .into(binding.ivMovieSelected)
    }
}