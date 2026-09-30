package com.example.mvvmnewsapp.ui

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader {
    fun loadImage(
        imageUrl: String,
        imageView: ImageView
    ) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}