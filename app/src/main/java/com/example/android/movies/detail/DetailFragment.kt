package com.example.android.movies.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.android.movies.R
import com.example.android.movies.model.Movie
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class DetailFragment : Fragment() {
    private lateinit var communicator: MoviesListDetailCommunicator
    private lateinit var coverImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var dateReleaseTextView: TextView
    private lateinit var ratingRatingBar: RatingBar
    private lateinit var descriptionTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        findScreenComponents(view)

        communicator =
            ViewModelProviders.of(activity!!).get(MoviesListDetailCommunicator::class.java)
        communicator.movieSelected.observe(this, Observer { movie ->
            setDataToScreen(view, movie)
        })
        return view
    }

    private fun findScreenComponents(view: View) {
        with(view) {
            coverImageView = findViewById(R.id.detail_movie_cover)
            titleTextView = findViewById(R.id.detail_movie_title)
            dateReleaseTextView = findViewById(R.id.detail_movie_release)
            ratingRatingBar = findViewById(R.id.detail_movie_rating)
            descriptionTextView = findViewById(R.id.detail_movie_description)
        }
    }

    private fun setDataToScreen(view: View, movie: Movie) {
        Glide.with(view)
            .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(coverImageView)

        titleTextView.text = movie.title

        dateReleaseTextView.text = movie.releaseDate

        ratingRatingBar.rating = (movie.voteAverage / 2).toFloat()

        descriptionTextView.text = movie.overview
    }
}