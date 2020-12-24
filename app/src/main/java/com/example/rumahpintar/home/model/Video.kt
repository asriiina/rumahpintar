package com.example.rumahpintar.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
        var nama: String? = null,
        var linkVideo: String? = null,
        var linkThumbnail: String? = null
) : Parcelable

