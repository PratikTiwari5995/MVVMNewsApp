package com.example.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmnewsapp.model.SavedArticle


@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: SavedArticle)

    @Query("SELECT * FROM saved_articles ORDER BY publishedAt DESC")
    fun getSavedArticles(): LiveData<List<SavedArticle>>

    @Query("DELETE FROM saved_articles WHERE url = :url")
    suspend fun deleteArticle(url: String)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_articles WHERE url = :url)")
    suspend fun isArticleSaved(url: String): Boolean

    @Query("SELECT url FROM saved_articles")
    fun getSavedArticleUrls(): LiveData<List<String>>
}