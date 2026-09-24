package com.example.mvvmnewsapp.model

import android.view.Display

enum class NewsCategory(
    val displayName: String,
    val apiValue: String
) {
    BUSINESS(
        displayName = "Business",
        apiValue = "business"
    ),

    ENTERTAINMENT(
        displayName = "Entertainment",
        apiValue = "entertainment"
    ),

    GENERAL(
        displayName = "General",
        apiValue = "general"
    ),

    HEALTH(
        displayName = "Health",
        apiValue = "health"
    ),

    SCIENCE(
        displayName = "Science",
        apiValue = "science"
    ),

    SPORTS(
        displayName = "Sports",
        apiValue = "sports"
    ),

    TECHNOLOGY(
        displayName = "Technology",
        apiValue = "technology"
    )
}