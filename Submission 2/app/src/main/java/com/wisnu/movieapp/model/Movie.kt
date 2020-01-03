package com.wisnu.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String? = null,
    val score: String? = null,
    val date: String? = null,
    val overview: String? = null,
    val poster: Int? = null
) : Parcelable