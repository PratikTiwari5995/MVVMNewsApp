package com.example.mvvmnewsapp.api

import com.example.mvvmnewsapp.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/v2/everything/")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}