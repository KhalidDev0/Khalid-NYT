package com.khalid.articles.presentation.utils

import com.khalid.articles.model.Article

data class ArticlesScreenState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
