package com.example.myimagefinder.Retrofit

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    val documents: MutableList<KakaoImageData>,
)

data class KakaoImageData(
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("display_sitename")
    val siteName: String,
    @SerializedName("datetime")
    val dateTime: String,

    var isLiked: Boolean = false
)

