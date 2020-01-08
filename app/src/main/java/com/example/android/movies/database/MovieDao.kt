package com.example.android.movies.database

import androidx.paging.DataSource
import androidx.room.*
import com.example.android.movies.model.Movie

@Dao
interface MoviesListDao {

    @Query("SELECT * FROM movies")
    fun getAll(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies WHERE title LIKE (:title)")
    fun findByTitle(title: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: List<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Update
    fun update(vararg movie: Movie)
}