package com.example.whattowatch

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String
    ): Response<MoviesResponse>
}