package com.khalid.database.di

import android.content.Context
import androidx.room.Room
import com.khalid.database.dao.ArticleDao
import com.khalid.database.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext ctx: Context): AppDatabase = Room
        .databaseBuilder(ctx, AppDatabase::class.java, "khalid.db")
        .fallbackToDestructiveMigration(false).build()

    @Provides
    @Singleton
    fun provideArticleDao(db: AppDatabase): ArticleDao = db.articleDao()
}