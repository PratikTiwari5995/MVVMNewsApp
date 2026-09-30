package com.example.mvvmnewsapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.databinding.ArticalItemBinding
import com.example.mvvmnewsapp.model.Article



class NewsAdapter(
    private var articles: List<Article>,
    private val listener: NewsArticleListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val savedArticles = mutableSetOf<String>()
    private val imageLoader = ImageLoader()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {

        val binding = ArticalItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {

        val article = articles[position]

        holder.binding.tvTitle.text = article.title
        holder.binding.tvDescription.text = article.description
        holder.binding.tvContent.text = article.content
        holder.binding.tvAuthor.text = article.author

        imageLoader.loadImage(
            article.urlToImage,
            holder.binding.ivArticleImage
        )

        if (savedArticles.contains(article.url)) {
            holder.binding.btnSave.setImageResource(
                R.drawable.ic_delete
            )

        } else {

            holder.binding.btnSave.setImageResource(
                R.drawable.ic_save
            )
        }

        holder.binding.btnSave.setOnClickListener {
            val isSaved = savedArticles.contains(article.url)
            listener.onSaveArticle(article, isSaved)
        }

        holder.binding.btnOpenLink.setOnClickListener {
            val intent = Intent(
                holder.itemView.context,
                WebViewActivity::class.java
            )
            intent.putExtra("url", article.url)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    fun updateSavedArticles(urls: List<String>) {
        savedArticles.clear()
        savedArticles.addAll(urls)
        notifyDataSetChanged()
    }

    class NewsViewHolder(
        val binding: ArticalItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}