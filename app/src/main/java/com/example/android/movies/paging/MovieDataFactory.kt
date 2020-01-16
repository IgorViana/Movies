package com.example.android.movies.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.android.movies.enumerator.MoviesRequest
import com.example.android.movies.enumerator.RequestState
import com.example.android.movies.model.Movie

class MovieDataFactory(
    private val language: String,
    private val requestStatus: MutableLiveData<RequestState>,
    private val typeMoviesRequest: MoviesRequest
) : DataSource.Factory<Int, Movie>() {

    companion object {

        fun createUpcomingFactory(language: String, requestStatus: MutableLiveData<RequestState>) =
            MovieDataFactory(language, requestStatus, MoviesRequest.UPCOMING)

        fun createPopularFactory(language: String, requestStatus: MutableLiveData<RequestState>) =
            MovieDataFactory(language, requestStatus, MoviesRequest.POPULAR)

    }


    override fun create(): DataSource<Int, Movie> {
        return MovieDataSource(language, requestStatus, typeMoviesRequest)
    }
}