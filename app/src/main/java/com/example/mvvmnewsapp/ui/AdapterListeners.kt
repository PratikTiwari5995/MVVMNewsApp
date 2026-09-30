package com.example.mvvmnewsapp.ui

import com.example.mvvmnewsapp.model.Article
import com.example.mvvmnewsapp.model.NewsCategory
import com.example.mvvmnewsapp.model.SavedArticle

interface NewsArticleListener {
    fun onSaveArticle(article: Article, isSaved: Boolean)
}

interface SavedArticleListener {
    fun onDeleteArticle(article: SavedArticle)
}

interface CategoryListener {
    fun onCategorySelected(category: NewsCategory)
}