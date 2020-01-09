package com.wisnu.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("original_title")
    var title: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("release_date")
    val date: String? = null,

    @SerializedName("tagline")
    val tagLine: String? = null,

    @SerializedName("poster_path")
    var poster: String? = null,

    @SerializedName("backdrop_path")
    val backdrop: String? = null,

    @SerializedName("vote_average")
    var score: String? = null
)
