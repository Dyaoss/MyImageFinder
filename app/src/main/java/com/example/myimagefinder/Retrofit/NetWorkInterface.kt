package com.example.myimagefinder.Retrofit

import com.example.myimagefinder.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface KakaoAPI {
    @GET("v2/search/image")
    fun searchImage(
        @Header("Authorization") apiKey: String = "KakaoAK ${BuildConfig.API_KEY}",
        @Query("query") query: String
    ): Call<ImageResponse>
}