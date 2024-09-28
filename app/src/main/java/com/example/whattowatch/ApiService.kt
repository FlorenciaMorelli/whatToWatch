package com.example.whattowatch


import com.example.whattowatch.model.Movie
import com.example.whattowatch.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //  Get all popular movies
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse

    //  Search for movies
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResponse

    //  Get movie details by ID
    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: Int, @Query("api_key") apiKey: String): Response<Movie>
}