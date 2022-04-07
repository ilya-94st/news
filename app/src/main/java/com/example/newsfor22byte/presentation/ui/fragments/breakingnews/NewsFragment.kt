package com.example.newsfor22byte.presentation.ui.fragments.breakingnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.viewbinding.ViewBinding
import com.example.newsfor22byte.databinding.FragmentNewsBinding
import com.example.newsfor22byte.presentation.adapters.ArticleLoadAdapter
import com.example.newsfor22byte.presentation.adapters.BreakingNewsAdapter
import com.example.newsfor22byte.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    private val viewModelNews: NewsViewModel by viewModels()
    private lateinit var articleAdapter: BreakingNewsAdapter
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentNewsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
                swipeRefresh()
        lifecycleScope.launchWhenCreated {
            viewModelNews.getBreakingNews().collectLatest {
                articleAdapter.submitData(it)
            }
        }
    }
    private fun initAdapter() {
        articleAdapter = BreakingNewsAdapter()
        binding.rvArticle.adapter = articleAdapter.withLoadStateFooter(
            footer = ArticleLoadAdapter()
        )

        articleAdapter.addLoadStateListener { state: CombinedLoadStates ->
            val refreshState = state.refresh
            binding.rvArticle.isVisible = refreshState != LoadState.Loading
            binding.paginationProgressBar.isVisible = refreshState == LoadState.Loading
            if (refreshState is LoadState.Error) {
                snackBar("you offline")
            }
        }
    }
    private fun swipeRefresh() {
        binding.swipe.setOnRefreshListener {
            articleAdapter.refresh()
            binding.swipe.isRefreshing = false
        }
    }
}