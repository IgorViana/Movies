package com.example.android.movies.repository

import android.util.Log
import com.example.android.movies.webService.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GenreRepository {

    private val compositeDisposable = CompositeDisposable()

    fun loadGenres() {

        val movieService = RetrofitFactory()
            .movieService()

        val disposable = movieService.getGenres()
            .doOnNext {  }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.i("TESTE", result.genres.toString())
                },
                { error ->
                    error.printStackTrace()
                    Log.i("ERROR", "ERRO")
                },
                {

                }
            )

        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

}