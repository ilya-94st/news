package com.example.newsfor22byte.data.pagersource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.newsfor22byte.common.Constants
import com.example.newsfor22byte.common.Constants.STARTING_PAGE_INDEX
import com.example.newsfor22byte.data.api.ApiNews
import com.example.newsfor22byte.domain.models.response.Article
import java.io.IOException

class ArticlePagingSource(
    private val api: ApiNews,
    private val country: String,
    private val category: String

): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getBreakingNews(country = country, category = category, page = position, pageSize = params.loadSize)
            val article = response.body()!!.articles
            val nextKey = if (article.isEmpty()) {
                null
            } else {
                position + (params.loadSize / Constants.PAGE_SIZE)
            }
            LoadResult.Page(
                data = article,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}