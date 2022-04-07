package com.example.newsfor22byte.domain.models.response

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)