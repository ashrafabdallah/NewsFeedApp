package com.example.feedapplication.presention.di

import android.app.Application
import androidx.room.Room
import com.example.feedapplication.data.db.NewsDao
import com.example.feedapplication.data.db.NewsRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDataBaseInstance(app: Application): NewsRoomDataBase {
        return Room.databaseBuilder(app, NewsRoomDataBase::class.java, "news_database")
            // This will Allow Room to destructively replace database tables, if Migrations, that would migrate old database
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticalDAo(newsRoomDataBase: NewsRoomDataBase): NewsDao {
        return newsRoomDataBase.getNewsDao()
    }
}