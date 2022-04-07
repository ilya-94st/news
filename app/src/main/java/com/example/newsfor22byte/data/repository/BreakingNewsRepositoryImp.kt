package com.example.newsfor22byte.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsfor22byte.common.Constants
import com.example.newsfor22byte.data.api.ApiNews
import com.example.newsfor22byte.data.pagersource.ArticlePagingSource
import com.example.newsfor22byte.domain.models.response.Article
import com.example.newsfor22byte.domain.repository.BreakingNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreakingNewsRepositoryImp @Inject constructor(private val apiNews: ApiNews): BreakingNewsRepository {

    override fun getBreakingNews(country: String, category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArticlePagingSource(apiNews, country = country, category = category) }
        ).flow
    }
}
