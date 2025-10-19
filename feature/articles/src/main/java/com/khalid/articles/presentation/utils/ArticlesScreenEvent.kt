package com.khalid.articles.presentation.utils

sealed class ArticlesScreenEvent {
    data class ChangePeriod(val period: Int) : ArticlesScreenEvent()
}