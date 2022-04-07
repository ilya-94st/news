package com.example.newsfor22byte.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfor22byte.R
import com.example.newsfor22byte.databinding.ItemsNewsBinding
import com.example.newsfor22byte.domain.models.response.Article

class BreakingNewsAdapter : PagingDataAdapter<Article, BreakingNewsAdapter.NewsViewHolder>(
    ARTICLE_COMPARATOR) {

    inner class NewsViewHolder(var binding: ItemsNewsBinding) : RecyclerView.ViewHolder(binding.root)

    companion object{
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemsNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.apply {
            if (currentItem?.urlToImage != null) {
                Glide.with(this).load(currentItem.urlToImage).into(holder.binding.ivArticleImage)
            } else {
                Glide.with(this).load(R.drawable.no_photo).into(holder.binding.ivArticleImage)
            }
                }

        if (currentItem != null) {
            holder.binding.tvDescription.text = currentItem.description
            holder.binding.tvPublishedAt.text = currentItem.publishedAt
            holder.binding.tvSource.text = currentItem.source?.name
            holder.binding.tvTitle.text = currentItem.title
        }

    }
  }
