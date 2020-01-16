package com.example.android.movies.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.movies.model.Movie

class MoviesListDetailCommunicator : ViewModel() {

    val movieSelected : MutableLiveData<Movie> by lazy {
        MutableLiveData<Movie>()
    }
}