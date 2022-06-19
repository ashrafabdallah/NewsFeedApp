package com.example.feedapplication.presention.di

import com.example.feedapplication.presention.adapter.HomeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeAdapterModule {
    @Singleton
    @Provides
    fun provideHomeAdapter(): HomeAdapter {
        return HomeAdapter()
    }
}