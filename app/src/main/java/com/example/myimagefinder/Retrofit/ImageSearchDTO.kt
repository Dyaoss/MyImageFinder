package com.example.myimagefinder.Retrofit

import com.google.gson.annotations.SerializedName

data class KakaoImageData(
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("display_sitename")
    val siteName: String,
    val datetime: String,
)

data class ImageResponse(
    val documents: List<KakaoImageData>
)