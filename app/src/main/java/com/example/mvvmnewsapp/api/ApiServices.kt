package com.example.mvvmnews.api

import com.example.mvvmnews.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/v2/everything/")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}