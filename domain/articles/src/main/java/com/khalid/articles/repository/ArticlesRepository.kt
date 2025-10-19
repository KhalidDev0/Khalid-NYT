package com.khalid.articles.repository

import com.khalid.articles.model.Article
import com.khalid.common.utils.AppResult
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getPopularArticles(period: Int): Flow<AppResult<List<Article>>>
    fun getCachedArticles(period: Int): Flow<AppResult<List<Article>>>
}