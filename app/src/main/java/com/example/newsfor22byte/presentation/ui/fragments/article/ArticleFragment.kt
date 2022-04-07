package com.example.newsfor22byte.presentation.ui.fragments.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.example.newsfor22byte.databinding.FragmentArticleBinding
import com.example.newsfor22byte.presentation.base.BaseFragment


class ArticleFragment : BaseFragment<FragmentArticleBinding>() {
    private val args: ArticleFragmentArgs by navArgs()
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentArticleBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = args.url

        webView(url)
    }
    private fun webView(urlArticle: String) {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(urlArticle)
        }
    }
}