package com.example.feedapplication.presention.di

import com.example.feedapplication.data.api.NewsApi
import com.example.feedapplication.data.repository.datasourse.RemoteDataSource
import com.example.feedapplication.data.repository.datasourseIMPL.RemoteDataSourceIMPL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providesRemoteResource(newsApi: NewsApi): RemoteDataSource {
        return RemoteDataSourceIMPL(newsApi)
    }
}