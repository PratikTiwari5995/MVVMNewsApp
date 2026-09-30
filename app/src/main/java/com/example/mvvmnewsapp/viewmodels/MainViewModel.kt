package com.example.mvvmnewsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnewsapp.model.Article
import com.example.mvvmnewsapp.model.SavedArticle
import com.example.mvvmnewsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    // Api Calls
    fun getNews(
        query: String,
        apiKey: String
    ) {
        viewModelScope.launch {
            val response = repository.getNews(
                query,
                apiKey
            )
            _articles.postValue(response.articles)
        }
    }

    // Room Functions

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            repository.saveArticle(article)
        }
    }

    fun getSavedArticles(): LiveData<List<SavedArticle>> {
        return repository.getSavedArticles()
    }

    fun deleteArticle(url: String) {
        viewModelScope.launch {
            repository.deleteArticle(url)
        }
    }

    fun getSavedArticleUrls(): LiveData<List<String>> {
        return repository.getSavedArticleUrls()
    }

    fun isArticleSaved(
        url: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val saved = repository.isArticleSaved(url)
            onResult(saved)
        }
    }

}