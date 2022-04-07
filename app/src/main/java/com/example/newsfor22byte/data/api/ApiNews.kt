package com.example.newsfor22byte.data.api

import com.example.newsfor22byte.common.Constants.API_KEY
import com.example.newsfor22byte.domain.models.response.NewsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

    interface ApiNews {

        @GET("v2/top-headlines")
        suspend fun getBreakingNews(
            @Query("country")
            country: String,
            @Query("category")
            category:String,
            @Query("pageSize")
            pageSize: Int,
            @Query("page")
            page: Int,
            @Query("apiKey")
            apiKey: String = API_KEY
        ): Response<NewsResponse>

        @Streaming
        @GET
        fun downloadFile(@Url fileUrl: String): Call<ResponseBody>
    }