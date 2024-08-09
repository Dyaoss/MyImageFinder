package com.example.myimagefinder.Retrofit

import com.example.myimagefinder.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface KakaoAPI {
    @Headers("Authorization: KakaoAK 5ae6a3bfb6f94faedb492a3be50c60a3")
    @GET("v2/search/image")
    fun getImgData(
        @Query("query") query: String
    ): Call<ImageResponse>

}