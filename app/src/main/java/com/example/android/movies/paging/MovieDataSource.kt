package com.example.android.movies.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.android.movies.enumerator.MoviesRequest
import com.example.android.movies.enumerator.NetworkState
import com.example.android.movies.enumerator.RequestState
import com.example.android.movies.model.Movie
import com.example.android.movies.model.MovieResult
import com.example.android.movies.webService.MovieService
import com.example.android.movies.webService.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource(
    private val language: String,
    private val requestStatus: MutableLiveData<RequestState>,
    private val typeMoviesRequest: MoviesRequest
) : PageKeyedDataSource<Int, Movie>() {

    private val movieApi: MovieService by lazy {
        RetrofitFactory().movieService()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {

        loadPopularMovies { movieResults, nextPage ->
            callback.onResult(movieResults.results, null, nextPage)
        }

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Movie>
    ) {
        loadPopularMovies(params.key) { movieResult, nextPage ->
            callback.onResult(movieResult.results, nextPage)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun loadPopularMovies(
        page: Int = 1,
        onSuccess: (result: MovieResult, nextPage: Int?) -> Unit
    ) {

        requestStatus.postValue(RequestState(typeMoviesRequest, NetworkState.LOADING))

        val call = defineCorretCall(page)

        call.enqueue(object : Callback<MovieResult> {
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        val nextPage =
                            if (page == response.body()!!.totalPages) null else page + 1

                        onSuccess(it, nextPage)

                        requestStatus.postValue(
                            RequestState(
                                typeMoviesRequest,
                                NetworkState.LOADED
                            )
                        )

                    } ?: requestStatus.postValue(
                        RequestState(typeMoviesRequest, NetworkState.FAILED)
                    )

                } else {
                    requestStatus.postValue(
                        RequestState(typeMoviesRequest, NetworkState.FAILED)
                    )
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                requestStatus.postValue(
                    RequestState(typeMoviesRequest, NetworkState.FAILED)
                )
            }
        })
    }

    private fun defineCorretCall(page: Int): Call<MovieResult> {

        return when (typeMoviesRequest) {
            MoviesRequest.POPULAR -> movieApi.getPopularMovies(
                page = page,
                language = language
            )

            MoviesRequest.UPCOMING -> movieApi.getUpcomingMovie(
                page = page,
                language = language
            )
        }
    }


}