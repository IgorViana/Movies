package com.example.android.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.movies.R
import com.example.android.movies.model.Movie

class MoviesListAdapter(private val movieListener: MovieListClickListener?)
    : PagedListAdapter<Movie, MoviesListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movielist, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val movieCover: ImageView = view.findViewById(R.id.item_movieList_cover)
        private val movieTitle: TextView = view.findViewById(R.id.item_movieList_title)

        fun bindTo(movie: Movie?) {

            if (movie != null) {

                Glide.with(view)
                    .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(movieCover)

                movieTitle.text = movie.title
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie: Movie? = getItem(position)
        holder.bindTo(movie)
        holder.itemView.setOnClickListener {
            movieListener?.onMovieClicked(movie!!)
        }

    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(
                oldConcert: Movie,
                newConcert: Movie
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: Movie,
                newConcert: Movie
            ) = oldConcert == newConcert
        }
    }
}
