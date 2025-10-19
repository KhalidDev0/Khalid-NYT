package com.khalid.nyt.repo

import com.khalid.articles.model.Article
import com.khalid.articles.repository.ArticlesRepository
import com.khalid.common.utils.AppResult
import com.khalid.database.dao.ArticleDao
import com.khalid.network.utils.SafeFlowHandler
import com.khalid.nyt.api.ArticlesService
import com.khalid.nyt.mapper.toDomain
import com.khalid.nyt.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val service: ArticlesService,
    private val dao: ArticleDao,
    private val safeFlowHandler: SafeFlowHandler,
) : ArticlesRepository {
    override fun getPopularArticles(period: Int): Flow<AppResult<List<Article>>> =
        safeFlowHandler {
            val resp = service.getMostPopularArticles(period)
            if (resp.results.isEmpty()) {
                AppResult.Success(dao.getMostViewedArticles(period).map { it.toDomain() })
            } else {
                val entities = resp.results.map { it.toEntity(period) }
                dao.clearPeriod(period)
                dao.upsertAll(entities)
                AppResult.Success(entities.map { it.toDomain() })
            }
        }

    override fun getCachedArticles(period: Int): Flow<AppResult<List<Article>>> = safeFlowHandler {
        AppResult.Success(dao.getMostViewedArticles(period).map { it.toDomain() })
    }
}