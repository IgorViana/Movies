package com.example.android.movies.model

data class MovieResult(val results: List<Movie>)

data class Movie(val title: String, val genre: String, val description: String)