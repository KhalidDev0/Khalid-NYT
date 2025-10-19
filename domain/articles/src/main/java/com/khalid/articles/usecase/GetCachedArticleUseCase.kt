package com.khalid.articles.usecase

import com.khalid.articles.model.Article
import com.khalid.articles.repository.ArticlesRepository
import com.khalid.common.utils.AppResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachedArticleUseCase @Inject constructor(
    private val repo: ArticlesRepository,
) {
    operator fun invoke(period: Int): Flow<AppResult<List<Article>>> = repo.getCachedArticles(period)
}