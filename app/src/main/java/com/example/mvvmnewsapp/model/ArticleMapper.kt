package com.example.mvvmnewsapp.model

fun Article.toSavedArticle(): SavedArticle {

    return SavedArticle(
        url = url,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceName = source.name,
        title = title,
        urlToImage = urlToImage
    )
}