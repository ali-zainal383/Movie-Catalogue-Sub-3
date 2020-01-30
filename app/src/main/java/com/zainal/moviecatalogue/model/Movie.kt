package com.zainal.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: Int = 0,
    var original_name: String? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var poster: String? = null
    ) : Parcelable