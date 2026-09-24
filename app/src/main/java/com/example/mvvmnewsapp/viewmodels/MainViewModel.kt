package com.example.mvvmnews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnews.model.Article
import com.example.mvvmnews.repository.NewsRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

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
}