package com.example.android.movies.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.adapter.MovieListClickListener
import com.example.android.movies.adapter.MoviesListAdapter
import com.example.android.movies.detail.DetailFragment
import com.example.android.movies.detail.MoviesListDetailCommunicator
import com.example.android.movies.enumerator.MoviesRequest
import com.example.android.movies.enumerator.NetworkState
import com.example.android.movies.model.Movie
import kotlinx.android.synthetic.main.fragment_movieslist.*

class MoviesListFragment : Fragment(), MovieListClickListener {

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var communicator: MoviesListDetailCommunicator


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movieslist, container, false)

        viewModel = ViewModelProviders.of(this).get(MoviesListViewModel::class.java)
        communicator =
            ViewModelProviders.of(activity!!).get(MoviesListDetailCommunicator::class.java)

        setPopularMoviesListRecyclerView(view)
        setUpcomingMoviesListRecyclerView(view)

        viewModel.listMovieStatus.observe(this, Observer { stateRequested ->
            if (stateRequested.type == MoviesRequest.POPULAR) {
                handleLoadingState(progressBarPopularMovies, stateRequested.state)
            }

            if (stateRequested.type == MoviesRequest.UPCOMING) {
                handleLoadingState(progressBarUpcomingMovies, stateRequested.state)
            }
        })

        view.findViewById<Button>(R.id.loadGenres).setOnClickListener {
            viewModel.loadGenres()
        }

        return view
    }

    private fun handleLoadingState(progressBar: ProgressBar, state: NetworkState) {
        when (state) {
            NetworkState.LOADING -> progressBar.visibility = View.VISIBLE
            NetworkState.LOADED -> progressBar.visibility = View.INVISIBLE
            NetworkState.FAILED -> {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(context, "Erro ao carregar dados", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setPopularMoviesListRecyclerView(view: View) {
        val mRecyclerView = view.findViewById<RecyclerView>(R.id.movieList_listPopularMovies)
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val mAdapter = MoviesListAdapter(this)

        with(mRecyclerView) {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        viewModel.popularMovieListLiveData.observe(this, Observer {
            mAdapter.submitList(it)
        })
    }

    private fun setUpcomingMoviesListRecyclerView(view: View) {
        val mRecyclerView = view.findViewById<RecyclerView>(R.id.movieList_listUpcomingMovies)
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val mAdapter = MoviesListAdapter(this)

        with(mRecyclerView) {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        viewModel.upcomingMovieListLiveData.observe(this, Observer {
            mAdapter.submitList(it)
        })
    }

    override fun onMovieClicked(movie: Movie) {
        communicator.movieSelected.postValue(movie)

        fragmentManager!!.beginTransaction()
            .replace(R.id.moviesList, DetailFragment())
            .addToBackStack(null)
            .commit()
    }
}

