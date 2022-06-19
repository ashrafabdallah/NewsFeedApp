package com.example.feedapplication.presention.di

import android.app.Application
import com.example.feedapplication.domain.usecase.RemoteUseCase.NewsFeedUseCase
import com.example.feedapplication.presention.viewmodel.Homeviewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeViewModelModule {

    @Singleton
    @Provides
    fun providesHomeViewModelFactory(
        app: Application, newsFeedUseCase: NewsFeedUseCase
    ): HomeViewModelFactory {
        return HomeViewModelFactory(app, newsFeedUseCase)
    }
}