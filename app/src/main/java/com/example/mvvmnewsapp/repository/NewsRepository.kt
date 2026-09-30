package com.example.mvvmnewsapp.repository

import androidx.lifecycle.LiveData
import com.example.mvvmnewsapp.api.ApiServices
import com.example.mvvmnewsapp.db.ArticleDao
import com.example.mvvmnewsapp.model.Article
import com.example.mvvmnewsapp.model.NewsResponse
import com.example.mvvmnewsapp.model.SavedArticle
import com.example.mvvmnewsapp.model.toSavedArticle

class NewsRepository(
    private val api: ApiServices,
    private val articleDao: ArticleDao
) {

    suspend fun getNews(
        query: String,
        apiKey: String
    ): NewsResponse {

        return api.getNews(
            query = query,
            apiKey = apiKey
        )
    }

    suspend fun saveArticle(article: Article) {
        articleDao.insertArticle(
            article.toSavedArticle()
        )
    }

    fun getSavedArticles(): LiveData<List<SavedArticle>> {
        return articleDao.getSavedArticles()
    }

    fun getSavedArticleUrls(): LiveData<List<String>> {
        return articleDao.getSavedArticleUrls()
    }

    suspend fun deleteArticle(url: String) {
        articleDao.deleteArticle(url)
    }

    suspend fun isArticleSaved(url: String): Boolean {
        return articleDao.isArticleSaved(url)
    }
}