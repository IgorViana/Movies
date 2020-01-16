package com.example.android.movies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.movies.database.MoviesListDao
import com.example.android.movies.enumerator.MoviesRequest
import com.example.android.movies.enumerator.RequestState
import com.example.android.movies.model.Movie
import com.example.android.movies.paging.MovieDataFactory
import java.util.*
import java.util.concurrent.Executors

class MoviesListRepository(private val movieDao: MoviesListDao) {

    val popularMovies: LiveData<PagedList<Movie>>
    val upcomingMovies: LiveData<PagedList<Movie>>

    val listMovieStatus = MutableLiveData<RequestState>()

    init {
        val defaultLanguage = getCurrentLanguage()
        val config = configurePagedListBuilder()

        val popularMovieDataSourceFactory = MovieDataFactory.createPopularFactory(defaultLanguage, listMovieStatus)
        val upcomingMovieDataSourceFactory = MovieDataFactory.createUpcomingFactory(defaultLanguage, listMovieStatus)


        popularMovies =
            LivePagedListBuilder<Int, Movie>(popularMovieDataSourceFactory, config)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build()

        upcomingMovies =
            LivePagedListBuilder<Int, Movie>(upcomingMovieDataSourceFactory, config)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build()
    }

    private fun configurePagedListBuilder(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20 * 2)
            .setEnablePlaceholders(true)
            .build()
    }

    private fun getCurrentLanguage(): String {
        val language = Locale.getDefault().language
        val country = Locale.getDefault().country
        return "${language}-${country}"
    }

    /*fun insert(movie: List<Movie>) {
        AsyncTask.execute { movieDao.insert(movie) }
    }*/

    /*fun searchMostPopularMovies() {

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
    }*/
}