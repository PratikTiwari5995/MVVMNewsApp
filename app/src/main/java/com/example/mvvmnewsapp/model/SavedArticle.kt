package com.example.mvvmnewsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class SavedArticle (
    @PrimaryKey
    val url: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
    val title: String,
    val urlToImage: String
    )