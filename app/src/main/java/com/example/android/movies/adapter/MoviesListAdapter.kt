package com.example.android.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.model.Movie

class MoviesListAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MoviesListAdapter.MyViewHolder>() {

    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movielist, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.item_movieList_title)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title

    }

    fun updateData(movies: List<Movie>?) {
        this.movies = movies.orEmpty()
        notifyDataSetChanged()
    }


}