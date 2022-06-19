package com.example.feedapplication.presention.di

import com.example.feedapplication.data.api.NewsApi
import com.example.feedapplication.data.db.NewsDao
import com.example.feedapplication.data.repository.datasourse.LocalDataSource
import com.example.feedapplication.data.repository.datasourseIMPL.LocalDataSourseIMPL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourseModule {
    @Singleton
    @Provides
    fun providesRemoteResource(newsDao: NewsDao):LocalDataSource{
        return LocalDataSourseIMPL(newsDao)
    }
}