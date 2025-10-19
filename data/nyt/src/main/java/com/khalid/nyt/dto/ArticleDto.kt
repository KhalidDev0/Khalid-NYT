package com.khalid.nyt.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val id: Long,
    val url: String,
    val byline: String? = null,
    val title: String,
    @SerialName("abstract") val summary: String,
    @SerialName("published_date") val publishedDate: String,
    val media: List<MediaDto>? = null
)

@Serializable
data class MediaDto(
    @SerialName("media-metadata")
    val metadata: List<MediaMetaDto>? = null,
)

@Serializable
data class MediaMetaDto(
    val url: String? = null,
    val format: String? = null,
)