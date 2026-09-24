package com.example.mvvmnews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmnews.databinding.ArticalItemBinding
import com.example.mvvmnews.model.Article

class NewsAdapter(
    private var articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()
{

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

        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.binding.ivArticleImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    class NewsViewHolder(val binding: ArticalItemBinding) : RecyclerView.ViewHolder(binding.root)

    }

