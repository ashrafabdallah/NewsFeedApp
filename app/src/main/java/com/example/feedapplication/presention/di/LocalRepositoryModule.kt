package com.example.feedapplication.presention.di

import com.example.feedapplication.data.repository.LocalRepositoryIMPL
import com.example.feedapplication.data.repository.RemoteRepositoryIMPL
import com.example.feedapplication.data.repository.datasourse.LocalDataSource
import com.example.feedapplication.data.repository.datasourse.RemoteDataSource
import com.example.feedapplication.domain.repository.local.LocalNewsRepository
import com.example.feedapplication.domain.u.repository.remote.RemoteNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalRepositoryModule {
    @Singleton
    @Provides
    fun providesLocalRepository(
        localDataSource: LocalDataSource
    ): LocalNewsRepository {
        return LocalRepositoryIMPL(localDataSource)
    }
}