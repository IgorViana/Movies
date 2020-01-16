package com.example.android.movies.moviesList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.movies.database.AppDatabase
import com.example.android.movies.enumerator.RequestState
import com.example.android.movies.model.Movie
import com.example.android.movies.repository.GenreRepository
import com.example.android.movies.repository.MoviesListRepository


class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MoviesListRepository
    private val genreRepository: GenreRepository

    val popularMovieListLiveData: LiveData<PagedList<Movie>>
    val upcomingMovieListLiveData: LiveData<PagedList<Movie>>
    val listMovieStatus: MutableLiveData<RequestState>

    init {

        val db = AppDatabase.getDatabase(getApplication())

        repository = MoviesListRepository(db.movieDao())
        genreRepository = GenreRepository()

        popularMovieListLiveData = repository.popularMovies
        upcomingMovieListLiveData = repository.upcomingMovies

        listMovieStatus = repository.listMovieStatus
    }

    override fun onCleared() {
        super.onCleared()
        genreRepository.dispose()
    }

    fun loadGenres(){
        genreRepository.loadGenres()
    }
}