package com.example.android.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "moviesResult")
data class MovieResult(
    @PrimaryKey(autoGenerate = true) val id: Long,

    val page: Int,
    @SerializedName("total_results") val totalResults: Long,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<Movie>
)

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false) val id: Long,

    val popularity: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val video: Boolean,
    @SerializedName("poster_path") val posterPath: String,
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    //@SerializedName("genre_ids") val genreId: Array<Int>,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String
)
