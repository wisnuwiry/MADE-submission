package com.wisnu.movieapp.network

import com.wisnu.movieapp.BuildConfig
import com.wisnu.movieapp.model.MovieDetail
import com.wisnu.movieapp.model.MovieResponse
import com.wisnu.movieapp.model.TVResponse
import com.wisnu.movieapp.model.TVShowDetail
import com.wisnu.movieapp.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface ApiService {
    companion object {
        private const val api_key: String = BuildConfig.API_KEY
    }

    @GET("discover/movie?api_key=$api_key")
    fun getDiscoverMovie(): Call<MovieResponse>

    @GET("movie/{id}?api_key=$api_key")
    fun getDetailMovie(@Path("id") id: String): Call<MovieDetail>

    @GET("discover/tv?api_key=$api_key")
    fun getDiscoverTV(): Call<TVResponse>

    @GET("tv/{id}?api_key=$api_key")
    fun getDetailTV(@Path("id") id: String): Call<TVShowDetail>
}