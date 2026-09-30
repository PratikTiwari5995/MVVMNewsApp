package com.example.mvvmnewsapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmnewsapp.databinding.ActivityMainBinding
import com.example.mvvmnewsapp.db.NewsDatabase
import com.example.mvvmnewsapp.model.Article
import com.example.mvvmnewsapp.model.NewsCategory
import com.example.mvvmnewsapp.model.SavedArticle
import com.example.mvvmnewsapp.viewmodels.MainViewModel
import com.example.mvvmnewsapp.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity(),
    NewsArticleListener,
    SavedArticleListener,
    CategoryListener {
    private lateinit var binding: ActivityMainBinding
    private val articles = mutableListOf<Article>()
    private val categories = NewsCategory.entries
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            NewsDatabase.getDatabase(this)
        )
    }
    private val newsAdapter = NewsAdapter(articles, this)
    private val savedArticleAdapter = SavedArticleAdapter(emptyList(), this)
    private val categoryAdapter = CategoryAdapter(categories, this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupTabs()
        observeNews()
        observeSavedArticles()
        observeSavedArticleUrls()
        getNews()
    }

    // NewsAdapter listener
    override fun onSaveArticle(
        article: Article,
        isSaved: Boolean
    ) {
        if (isSaved) {
            viewModel.deleteArticle(article.url)
        } else {
            viewModel.saveArticle(article)
        }
    }


    // SavedArticleAdapter listener
    override fun onDeleteArticle(article: SavedArticle) {
        viewModel.deleteArticle(article.url)
    }


    // CategoryAdapter listener
    override fun onCategorySelected(category: NewsCategory) {
        viewModel.getNews(
            query = category.apiValue,
            apiKey = "5cd746aa0eb9405db76e703f1916edbb"
        )
    }


    private fun setupTabs() {

        binding.btnNews.setOnClickListener {
            binding.recyclerView.visibility = android.view.View.VISIBLE
            binding.savedRecyclerView.visibility = android.view.View.GONE
        }

        binding.btnSaved.setOnClickListener {
            binding.recyclerView.visibility = android.view.View.GONE
            binding.savedRecyclerView.visibility = android.view.View.VISIBLE
        }
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

        // Saved RecyclerView

        binding.savedRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.savedRecyclerView.adapter = savedArticleAdapter
    }

    private fun observeNews() {
        viewModel.articles.observe(this) { newArticles ->
            newsAdapter.updateArticles(newArticles)
        }
    }

    private fun observeSavedArticles() {
        viewModel.getSavedArticles().observe(this) { savedArticles ->
            savedArticleAdapter.updateArticles(savedArticles)
        }
    }

    private fun observeSavedArticleUrls() {
        viewModel.getSavedArticleUrls().observe(this) { urls ->
            newsAdapter.updateSavedArticles(urls)
        }
    }

    private fun getNews() {
        viewModel.getNews(
            query = NewsCategory.TECHNOLOGY.apiValue,
            apiKey = "5cd746aa0eb9405db76e703f1916edbb"
        )
    }
}