package com.example.android.movies.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.adapter.MoviesListAdapter
import com.example.android.movies.model.Movie
import kotlinx.android.synthetic.main.fragment_movieslist.view.*

class MoviesListFragment : Fragment() {

    private lateinit var model: MoviesListViewModel
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: MoviesListAdapter
    private lateinit var mRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movieslist, container, false)
        model = ViewModelProviders.of(this).get(MoviesListViewModel::class.java)

        setMoviesListRecyclerView(view)

        view.botaoTeste.setOnClickListener {
            model.searchMostPopularMovies()
        }
        return view
    }

    private fun setMoviesListRecyclerView(view: View) {
        mRecyclerView = view.findViewById(R.id.movieList_listPopularMovies)
        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mAdapter = MoviesListAdapter()

        with(mRecyclerView) {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        model.movieListLiveData.observe(this, Observer{
            mAdapter.submitList(it)
        })
    }
}

