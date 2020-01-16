package com.example.android.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class GenreResult(val genres: List<Genre>)

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey val id: Int,
    val name: String
)