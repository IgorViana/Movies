package com.example.android.movies.moviesList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.room.Room
import com.example.android.movies.database.AppDatabase
import com.example.android.movies.model.Movie
import com.example.android.movies.model.MovieResult
import com.example.android.movies.repository.MoviesListRepository
import com.example.android.movies.webService.RetrofitFactory
import retrofit2.Call
import retrofit2.Response


class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MoviesListRepository

    val movieListLiveData: LiveData<PagedList<Movie>>

    init {
        val db = AppDatabase.getDatabase(getApplication())
        repository = MoviesListRepository(db.movieDao())
        movieListLiveData = repository.allMovies
    }

    fun searchMostPopularMovies() {

        val call = RetrofitFactory()
            .movieService()
            .getPopularMovies()

        call.enqueue(object : retrofit2.Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if (response.isSuccessful) {
                    //repository.insert(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Log.e("ERROR", t.message ?: "")
            }
        })
    }
}