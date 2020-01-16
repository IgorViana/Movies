package com.example.android.movies.enumerator

class RequestState(val type: MoviesRequest, val state: NetworkState) {

    fun getLaodingState(type: MoviesRequest) = RequestState(type, NetworkState.LOADING)

    fun getLoadedState(type: MoviesRequest) = RequestState(type, NetworkState.LOADED)

    fun getFailedState(type: MoviesRequest) = RequestState(type, NetworkState.FAILED)

}
