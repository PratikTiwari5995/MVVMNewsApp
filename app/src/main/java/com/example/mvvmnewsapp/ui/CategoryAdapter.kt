package com.example.mvvmnewsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmnews.databinding.ItemsCategoryBinding
import com.example.mvvmnewsapp.model.NewsCategory

class CategoryAdapter(
    private val categories: List<NewsCategory>,
    private val onCategoryClick: (NewsCategory) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding = ItemsCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val category = categories[position]
        holder.binding.tvCategory.text = category.displayName
        holder.binding.root.setOnClickListener {
            onCategoryClick(category)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryViewHolder(
        val binding: ItemsCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)
}