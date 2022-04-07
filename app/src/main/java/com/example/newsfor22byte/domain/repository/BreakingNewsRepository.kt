package com.example.newsfor22byte.domain.repository

import androidx.paging.PagingData
import com.example.newsfor22byte.domain.models.response.Article
import kotlinx.coroutines.flow.Flow


interface BreakingNewsRepository {

    fun getBreakingNews(country: String, category: String): Flow<PagingData<Article>>

}