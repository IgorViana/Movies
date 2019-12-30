package com.example.android.movies

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.movies.model.Movie
import com.example.android.movies.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response


class MoviesListView : ViewModel() {

    val API_KEY = "770e90d78673c82ce8fe6e01a71e7948"

    val movieLiveData: MutableLiveData<Movie> by lazy {
        MutableLiveData<Movie>()
    }


    fun searchMostPopularMovies() {
        movieLiveData.value = Movie("Percy Jackson", "Fantasia", "Descricao")


        /*val call = RetrofitBuilder()
            .movieService()
            .getPopularMovies()

        call.enqueue(object : retrofit2.Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.i("FALHA", t.message)
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
            }

        })*/

    }

    fun searchLatestMovie(){
        val call = RetrofitBuilder()
            .movieService()
            .getLatestMovie(API_KEY)

        call.enqueue(object: retrofit2.Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                movieLiveData.postValue(response.body())
            }

        })
    }
}