package com.khalid.articles.presentation.viewModel

import com.khalid.articles.presentation.utils.ArticlesScreenEvent
import com.khalid.articles.presentation.utils.ArticlesScreenState
import com.khalid.articles.usecase.GetCachedArticleUseCase
import com.khalid.articles.usecase.GetPopularArticlesUseCase
import com.khalid.common.base.BaseViewModel
import com.khalid.common.utils.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getPopularArticlesUseCase: GetPopularArticlesUseCase,
    private val getCachedArticleUseCase: GetCachedArticleUseCase,
) : BaseViewModel() {

    private val _state = MutableStateFlow(ArticlesScreenState())
    val state = _state.asStateFlow()

    init {
        getMostViewedArticles()
    }

    fun onEvent(event: ArticlesScreenEvent) {
        when (event) {
            is ArticlesScreenEvent.ChangePeriod -> TODO()
        }
    }

    private fun getMostViewedArticles() {
        onUseCaseUse(
            useCase = {
                getPopularArticlesUseCase(30)
            },
            onLoading = {
                _state.update {
                    copy(isLoading = it)
                }
            },
            onSuccess = { articles ->
                _state.update {
                    copy(articles = articles)
                }
            },
            onError = {
                getCachedArticle()
            }
        )

    }

    private fun getCachedArticle() {
        onUseCaseUse(
            useCase = {
                getCachedArticleUseCase(30)
            },
            onLoading = {
                _state.update {
                    copy(isLoading = it)
                }
            },
            onSuccess = { articles ->
                if (articles.isEmpty()) {
                    _state.update {
                        copy(errorMessage = "No Articles Available")
                    }
                } else {
                    _state.update {
                        copy(articles = articles)
                    }
                }
            },
            onError = {
                _state.update {
                    copy(errorMessage = "Something Went wrong...")
                }
            }
        )
    }
}