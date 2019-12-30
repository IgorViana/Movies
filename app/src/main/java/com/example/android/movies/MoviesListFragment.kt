package com.example.android.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.movies.model.Movie
import kotlinx.android.synthetic.main.fragment_movieslist.view.*

class MoviesListFragment : Fragment() {
    private lateinit var model: MoviesListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movieslist, container, false)
        view.movieList_title.text = "Estou no fragment"
        model = ViewModelProviders.of(this).get(MoviesListView::class.java)

        val observer = Observer<Movie> {
            view.movieList_title.text = it.title
        }
        model.movieLiveData.observe(this, observer)

        view.botaoTeste.setOnClickListener { model.searchLatestMovie() }
        return view
    }

}

