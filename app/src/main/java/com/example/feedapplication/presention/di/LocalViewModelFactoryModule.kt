package com.example.feedapplication.presention.di

import android.app.Application
import com.example.feedapplication.domain.usecase.LocalUseCase.DeleteNewsByIDUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.GetNewsByIdUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.GetSavedNewsUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.SaveNewsUseCase
import com.example.feedapplication.domain.usecase.RemoteUseCase.NewsFeedUseCase
import com.example.feedapplication.presention.viewmodel.localviewmodel.LocalViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalViewModelFactoryModule {
    @Singleton
    @Provides
    fun providesLocalViewModelFactory(
        app: Application, saveNewsUseCase: SaveNewsUseCase,
        getSavedNewsUseCase: GetSavedNewsUseCase, deleteNewsByIDUseCase: DeleteNewsByIDUseCase,
        getNewsByIdUseCase: GetNewsByIdUseCase
    ): LocalViewModelFactory {
        return LocalViewModelFactory(
            app,
            saveNewsUseCase,
            getSavedNewsUseCase,
            deleteNewsByIDUseCase,
            getNewsByIdUseCase
        )
    }
}