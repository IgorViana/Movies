package com.example.android.movies.webService


import com.example.android.movies.model.Movie
import com.example.android.movies.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

val API_KEY = "770e90d78673c82ce8fe6e01a71e7948"
val DEFAULT_LANGUAGE = "en-US"

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<MovieResult>

    @GET("movie/latest")
    fun getLatestMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<Movie>
}