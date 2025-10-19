package com.khalid.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khalid.database.dao.ArticleDao
import com.khalid.database.entities.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}