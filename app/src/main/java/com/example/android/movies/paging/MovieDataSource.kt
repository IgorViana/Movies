package com.example.android.movies.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.android.movies.model.Movie
import com.example.android.movies.model.MovieResult
import com.example.android.movies.webService.MovieService
import com.example.android.movies.webService.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource : PageKeyedDataSource<Int, Movie>() {

    private val movieApi: MovieService by lazy {
        RetrofitFactory().movieService()
    }

    private val status: MutableLiveData<Movie> by lazy {
        MutableLiveData<Movie>()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        movieApi.getPopularMovies(page = 1).enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!.results, null, 2)
                } else {

                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        movieApi.getPopularMovies(page = params.key).enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if (response.isSuccessful) {
                    val nextKey =
                        if (params.key == response.body()!!.totalPages) null else params.key + 1
                    callback.onResult(response.body()!!.results, nextKey)
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}