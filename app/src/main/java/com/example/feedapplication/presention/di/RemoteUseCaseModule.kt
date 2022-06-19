package com.example.feedapplication.presention.di

import com.example.feedapplication.domain.u.repository.remote.RemoteNewsRepository
import com.example.feedapplication.domain.usecase.RemoteUseCase.NewsFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteUseCaseModule {
    @Singleton
    @Provides
    fun providesGetNewsUseCase(repository: RemoteNewsRepository): NewsFeedUseCase {
        return NewsFeedUseCase(repository)
    }
}