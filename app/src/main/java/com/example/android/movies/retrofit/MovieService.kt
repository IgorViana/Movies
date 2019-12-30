package com.example.android.movies.retrofit


import com.example.android.movies.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/latest")
    fun getLatestMovie(@Query ("api_key") apiKey: String): Call<Movie>
}