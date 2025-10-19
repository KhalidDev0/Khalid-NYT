package com.khalid.nyt.di

import com.khalid.articles.repository.ArticlesRepository
import com.khalid.database.dao.ArticleDao
import com.khalid.network.utils.SafeFlowHandler
import com.khalid.nyt.api.ArticlesService
import com.khalid.nyt.repo.ArticlesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ArticlesModule {
    @Provides
    fun provideArticlesRepository(
        service: ArticlesService,
        dao: ArticleDao,
        safeFlowHandler: SafeFlowHandler,
    ): ArticlesRepository = ArticlesRepositoryImpl(
        service = service,
        dao = dao,
        safeFlowHandler = safeFlowHandler
    )
}