package com.example.android.movies.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.movies.database.MoviesListDao
import com.example.android.movies.model.Movie
import com.example.android.movies.paging.MovieDataFactory
import java.util.concurrent.Executors

class MoviesListRepository(private val movieDao: MoviesListDao) {

    val allMovies: LiveData<PagedList<Movie>>

    init {
        val movieDataSourceFactory = MovieDataFactory()
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20 * 2)
            .setEnablePlaceholders(true)
            .build()
        allMovies =
            LivePagedListBuilder<Int, Movie>(movieDataSourceFactory, config)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build()
    }

    fun insert(movie: List<Movie>) {
        AsyncTask.execute { movieDao.insert(movie) }
    }
}