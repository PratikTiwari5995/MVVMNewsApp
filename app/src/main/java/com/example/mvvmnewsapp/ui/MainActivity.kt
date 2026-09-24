package com.example.mvvmnews.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmnews.databinding.ActivityMainBinding
import com.example.mvvmnews.model.Article
import com.example.mvvmnews.viewmodels.MainViewModel
import com.example.mvvmnews.viewmodels.MainViewModelFactory
import com.example.mvvmnewsapp.model.NewsCategory
import com.example.mvvmnewsapp.ui.CategoryAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val articles = mutableListOf<Article>()
    private val newsAdapter = NewsAdapter(articles)
    private val categories = NewsCategory.entries
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }
    private val categoryAdapter = CategoryAdapter(categories) { category ->
        viewModel.getNews(  // Triggers By the Category Adapter
            query = category.apiValue,
            apiKey = "5cd746aa0eb9405db76e703f1916edbb"
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeNews()
        getNews()
    }

    private fun setupRecyclerView() {

        // Article RecyclerView
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this)

        binding.recyclerView.adapter = newsAdapter


        // Category RecyclerView
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        binding.categoryRecyclerView.adapter =
            categoryAdapter
    }

    private fun observeNews() {

        viewModel.articles.observe(this) { newArticles ->
            newsAdapter.updateArticles(newArticles)
        }
    }

    private fun getNews() {
        viewModel.getNews(
            query = NewsCategory.TECHNOLOGY.apiValue,
            apiKey = "5cd746aa0eb9405db76e703f1916edbb"
        )
    }
}