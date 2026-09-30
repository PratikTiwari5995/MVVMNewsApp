package com.example.mvvmnewsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmnewsapp.api.RetrofitHelper
import com.example.mvvmnewsapp.db.NewsDatabase
import com.example.mvvmnewsapp.repository.NewsRepository


class MainViewModelFactory(
    private val database: NewsDatabase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = NewsRepository(
            RetrofitHelper.apiService,
            database.articleDao()
        )
        return MainViewModel(repository) as T
    }
}