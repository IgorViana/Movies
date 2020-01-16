package com.example.android.movies.adapter

import com.example.android.movies.model.Movie

interface MovieListClickListener {
    fun onMovieClicked(movie: Movie)
}