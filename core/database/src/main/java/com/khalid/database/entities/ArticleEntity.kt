package com.khalid.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey val id: Long,
    val period: Int,
    val title: String,
    val byline: String?,
    val summary: String,
    val url: String,
    val publishedDate: String,
    val imageUrl: String?
)