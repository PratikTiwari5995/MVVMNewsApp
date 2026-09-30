package com.example.mvvmnewsapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.databinding.ArticalItemBinding
import com.example.mvvmnewsapp.model.SavedArticle


class SavedArticleAdapter(
    private var articles: List<SavedArticle>,
    private val listener: SavedArticleListener
): RecyclerView.Adapter<SavedArticleAdapter.SavedArticleViewHolder>() {

    private val imageLoader = ImageLoader()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedArticleViewHolder {
       val binding = ArticalItemBinding.inflate(LayoutInflater.from(parent.context),
           parent,
           false)
        return SavedArticleViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: SavedArticleViewHolder,
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

        holder.binding.btnSave.setImageResource(R.drawable.ic_delete)
        holder.binding.btnSave.setOnClickListener {
            listener.onDeleteArticle(article)
        }

        holder.binding.btnOpenLink.setOnClickListener {
            val intent = Intent(holder.itemView.context,
                WebViewActivity::class.java)
            intent.putExtra("url", article.url)
            holder.itemView.context.startActivity(intent)
        }
    }

    fun updateArticles(newArticles: List<SavedArticle>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return articles.size
    }

    class SavedArticleViewHolder(val binding: ArticalItemBinding) : RecyclerView.ViewHolder(binding.root)
}