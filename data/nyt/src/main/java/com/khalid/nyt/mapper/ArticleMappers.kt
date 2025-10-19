package com.khalid.nyt.mapper

import com.khalid.articles.model.Article
import com.khalid.database.entities.ArticleEntity
import com.khalid.nyt.dto.ArticleDto

fun ArticleDto.toEntity(period: Int) = ArticleEntity(
    id = id,
    period = period,
    title = title,
    byline = byline,
    summary = summary,
    url = url,
    publishedDate = publishedDate,
    imageUrl = media?.firstOrNull()?.metadata?.lastOrNull()?.url
)


fun ArticleEntity.toDomain() = Article(
    id = id,
    title = title,
    byline = byline,
    summary = summary,
    url = url,
    publishedDate = publishedDate,
    imageUrl = imageUrl
)