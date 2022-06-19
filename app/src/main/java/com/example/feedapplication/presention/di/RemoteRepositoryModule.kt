package com.example.feedapplication.presention.di

import com.example.feedapplication.data.repository.RemoteRepositoryIMPL
import com.example.feedapplication.data.repository.datasourse.RemoteDataSource
import com.example.feedapplication.domain.u.repository.remote.RemoteNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteRepositoryModule {
    @Singleton
    @Provides
    fun providesNewsRepository(
        remoteDataSource: RemoteDataSource
    ): RemoteNewsRepository {
        return RemoteRepositoryIMPL(remoteDataSource)
    }
}