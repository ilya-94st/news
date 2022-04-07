package com.example.newsfor22byte.presentation.ui.fragments.breakingnews

import androidx.lifecycle.ViewModel
import com.example.newsfor22byte.common.Constants
import com.example.newsfor22byte.domain.use_case.GetBreakingNews
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getBreakingNews: GetBreakingNews): ViewModel() {

    fun getBreakingNews() = getBreakingNews.invoke(Constants.COUNTRY, Constants.CATEGORY)
}