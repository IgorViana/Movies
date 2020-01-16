package com.example.android.movies.webService


import com.example.android.movies.model.GenreResult
import com.example.android.movies.model.MovieResult
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "770e90d78673c82ce8fe6e01a71e7948"
const val DEFAULT_LANGUAGE = "en-US"

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<MovieResult>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<MovieResult>

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String = API_KEY): Observable<GenreResult>

}