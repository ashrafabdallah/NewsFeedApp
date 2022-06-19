package com.example.feedapplication.presention.di

import com.example.feedapplication.domain.repository.local.LocalNewsRepository
import com.example.feedapplication.domain.usecase.LocalUseCase.DeleteNewsByIDUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.GetNewsByIdUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.GetSavedNewsUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalUseCaseModule {
    @Singleton
    @Provides
    fun providesSavedNewsUseCase(localNewsRepository: LocalNewsRepository): SaveNewsUseCase {
        return SaveNewsUseCase(localNewsRepository)
    }

    @Singleton
    @Provides
    fun providesGetNewsUseCase(localNewsRepository: LocalNewsRepository): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(localNewsRepository)
    }

    @Singleton
    @Provides
    fun providesDeleteNewsByIDUseCase(localNewsRepository: LocalNewsRepository): DeleteNewsByIDUseCase {
        return DeleteNewsByIDUseCase(localNewsRepository)
    }

    @Singleton
    @Provides
    fun providesGetNewsByIDUseCase(localNewsRepository: LocalNewsRepository): GetNewsByIdUseCase {
        return GetNewsByIdUseCase(localNewsRepository)
    }
}