package com.example.android.movies.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.android.movies.model.Movie

class MovieDataFactory : DataSource.Factory<Int, Movie>() {

    private val moviesDataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val moviesDataSource = MovieDataSource()
        moviesDataSourceLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }
}