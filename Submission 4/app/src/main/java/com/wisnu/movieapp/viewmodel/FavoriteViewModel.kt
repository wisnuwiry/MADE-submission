package com.wisnu.movieapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wisnu.movieapp.helper.MappingHelper
import com.wisnu.movieapp.helper.MovieHelper
import com.wisnu.movieapp.helper.TvHelper
import com.wisnu.movieapp.model.MovieResponse
import com.wisnu.movieapp.model.TVResponse
import com.wisnu.movieapp.model.TVShowDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private var data = MutableLiveData<MovieResponse>()
    private var dataTv = MutableLiveData<TVResponse>()
    private lateinit var movieHelper: MovieHelper
    private lateinit var tvHelper: TvHelper

    private fun getMovie(context: Context) {
        movieHelper = MovieHelper.getInstance(context)
        GlobalScope.launch(Dispatchers.Main) {
            val deferredMovie = async(Dispatchers.IO) {
                val cursor = movieHelper.getAll()
                MappingHelper.toListMovie(cursor)
            }

            val movies = deferredMovie.await()
            if (movies.isNotEmpty()) {
                val movieResponse = MovieResponse(1, movies.size, 1, movies)
                data.postValue(movieResponse)
            } else {
                val movieResponse = MovieResponse(1, movies.size, 1, ArrayList())
                data.postValue(movieResponse)
            }
        }
    }

    private fun getTvShow(context: Context) {
        tvHelper = TvHelper.getInstance(context)
        GlobalScope.launch(Dispatchers.Main) {
            val deferredTv = async(Dispatchers.IO) {
                val cursor = tvHelper.getAll()
                MappingHelper.toListTv(cursor)
            }

            val tvShow = deferredTv.await()
            if (tvShow.isNotEmpty()) {
                val tvResponse = TVResponse(1, tvShow.size, 1, tvShow)
                dataTv.postValue(tvResponse)
            } else {
                val tvResponse = TVResponse(1, tvShow.size, 1, ArrayList())
                dataTv.postValue(tvResponse)
            }
        }
    }

    fun setDataMovie(context: Context): MutableLiveData<MovieResponse> {
        getMovie(context)
        return data
    }

    fun setDataTv(context: Context):MutableLiveData<TVResponse>{
        getTvShow(context)
        return dataTv
    }
}