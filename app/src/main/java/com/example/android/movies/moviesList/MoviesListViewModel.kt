package com.example.android.movies.moviesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.movies.model.Movie
import com.example.android.movies.model.MovieResult
import com.example.android.movies.webService.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response


class MoviesListViewModel : ViewModel() {

    val movieListLiveData: MutableLiveData<MovieResult> by lazy {
        MutableLiveData<MovieResult>()
    }

    val movieLiveData: MutableLiveData<Movie> by lazy {
        MutableLiveData<Movie>()
    }


    fun searchMostPopularMovies() {
        val call = RetrofitBuilder()
            .movieService()
            .getPopularMovies()

        call.enqueue(object : retrofit2.Callback<MovieResult> {
            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                movieListLiveData.postValue(response.body())
            }
        })
    }

    fun searchLatestMovie() {
        val call = RetrofitBuilder()
            .movieService()
            .getLatestMovie()

        call.enqueue(object : retrofit2.Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                movieLiveData.postValue(response.body())
            }
        })
    }
}