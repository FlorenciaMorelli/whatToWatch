package com.example.whattowatch


import com.example.whattowatch.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>
}