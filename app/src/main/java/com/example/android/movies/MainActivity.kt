package com.example.android.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.movies.moviesList.MoviesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.moviesList,
                MoviesListFragment()
            )
            .commit()
    }
}
