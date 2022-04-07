package com.example.newsfor22byte.domain.use_case


import com.example.newsfor22byte.domain.repository.BreakingNewsRepository
import javax.inject.Inject

class GetBreakingNews @Inject constructor(private val breakingNewsRepository: BreakingNewsRepository) {

    operator fun invoke(country: String) = breakingNewsRepository.getBreakingNews(country)

}