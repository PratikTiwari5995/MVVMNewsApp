package com.example.mvvmnews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmnews.api.RetrofitHelper
import com.example.mvvmnews.repository.NewsRepository

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = NewsRepository(
            RetrofitHelper.apiService
        )
        return MainViewModel(repository) as T
    }
}