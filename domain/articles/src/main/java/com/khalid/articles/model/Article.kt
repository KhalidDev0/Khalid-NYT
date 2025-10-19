package com.khalid.articles.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Long,
    val title: String,
    val byline: String? = null,
    val summary: String,
    val url: String,
    val publishedDate: String,
    val imageUrl: String? = null
)