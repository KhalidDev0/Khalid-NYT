package com.khalid.nyt.repo

import com.khalid.articles.model.Article
import com.khalid.common.utils.AppResult
import com.khalid.database.dao.ArticleDao
import com.khalid.database.entities.ArticleEntity
import com.khalid.network.utils.SafeFlowHandler
import com.khalid.nyt.MainDispatcherRule
import com.khalid.nyt.api.ArticlesService
import com.khalid.nyt.dto.ArticleDto
import com.khalid.nyt.dto.PopularArticlesResponse
import io.mockk.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticlesRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var service: ArticlesService
    private lateinit var dao: ArticleDao
    private lateinit var safeFlowHandler: SafeFlowHandler
    private lateinit var repo: ArticlesRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        service = mockk(relaxed = true)
        dao = mockk(relaxed = true)
        safeFlowHandler = mockk()

        every {
            safeFlowHandler.invoke(any<suspend () -> AppResult<List<Article>>>())
        } answers {
            val block = firstArg<suspend () -> AppResult<List<Article>>>()
            flow { emit(block()) }
        }

        repo = ArticlesRepositoryImpl(service, dao, safeFlowHandler)
    }

    @After
    fun tearDown() = clearAllMocks()

    @Test
    fun `getCachedArticles returns Success with mapped items`() = runTest {
        val period = 7

        val e1 = mockk<ArticleEntity>(relaxed = true)
        val e2 = mockk<ArticleEntity>(relaxed = true)
        coEvery { dao.getMostViewedArticles(period) } returns listOf(e1, e2)

        val result = repo.getCachedArticles(period).first()
        assertTrue(result is AppResult.Success)
        assertEquals(2, (result as AppResult.Success).data.size)

        coVerify(exactly = 1) { dao.getMostViewedArticles(period) }
    }

    @Test
    fun `getPopularArticles falls back to DB when network is empty`() = runTest {
        val period = 1

        val response = mockk<PopularArticlesResponse>(relaxed = true)
        every { response.results } returns emptyList()
        coEvery { service.getMostPopularArticles(period) } returns response

        val e = mockk<ArticleEntity>(relaxed = true)
        coEvery { dao.getMostViewedArticles(period) } returns listOf(e)

        val result = repo.getPopularArticles(period).first()
        assertTrue(result is AppResult.Success)
        assertEquals(1, (result as AppResult.Success).data.size)

        coVerify(exactly = 0) { dao.clearPeriod(any()) }
        coVerify(exactly = 0) { dao.upsertAll(any()) }
        coVerify(exactly = 1) { dao.getMostViewedArticles(period) }
    }

    @Test
    fun `getPopularArticles saves and emits network data when results exist`() = runTest {
        val period = 30

        val dto1 = mockk<ArticleDto>(relaxed = true)
        val dto2 = mockk<ArticleDto>(relaxed = true)
        val response = mockk<PopularArticlesResponse>(relaxed = true)
        every { response.results } returns listOf(dto1, dto2)
        coEvery { service.getMostPopularArticles(period) } returns response
        coEvery { dao.clearPeriod(period) } just Runs
        coEvery { dao.upsertAll(match { it.size == 2 }) } just Runs

        val result = repo.getPopularArticles(period).first()
        assertTrue(result is AppResult.Success)
        assertEquals(2, (result as AppResult.Success).data.size)

        coVerifyOrder {
            dao.clearPeriod(period)
            dao.upsertAll(withArg { list -> assertEquals(2, list.size) })
        }
        coVerify(exactly = 0) { dao.getMostViewedArticles(any()) }
    }
}
