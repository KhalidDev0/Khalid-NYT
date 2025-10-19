package com.khalid.nyt.dto

import kotlinx.serialization.Serializable

@Serializable
data class PopularArticlesResponse(
    val status: String,
    val results: List<ArticleDto>,
)