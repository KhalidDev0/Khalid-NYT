package com.khalid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khalid.database.entities.ArticleEntity

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article WHERE period = :period ORDER BY publishedDate DESC")
    suspend fun getMostViewedArticles(period: Int): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ArticleEntity>)

    @Query("DELETE FROM article WHERE period = :period")
    suspend fun clearPeriod(period: Int)
}