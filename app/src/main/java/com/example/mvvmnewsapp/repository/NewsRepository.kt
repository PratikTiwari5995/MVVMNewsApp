package com.example.mvvmnews.repository

import com.example.mvvmnews.api.ApiServices
import com.example.mvvmnews.model.NewsResponse

class NewsRepository(private val api: ApiServices) {

    suspend fun getNews(
        query: String,
        apiKey: String
    ): NewsResponse {

        return api.getNews(
            query = query,
            apiKey = apiKey
        )
    }
}