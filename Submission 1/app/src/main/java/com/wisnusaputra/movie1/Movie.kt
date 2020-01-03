package com.wisnusaputra.movie1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var photo: Int,
    var name: String,
    var desc: String
) : Parcelable