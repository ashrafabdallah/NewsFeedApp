package com.example.feedapplication.data.repository

import com.example.feedapplication.data.model.Result
import com.example.feedapplication.data.repository.datasourse.LocalDataSource
import com.example.feedapplication.domain.repository.local.LocalNewsRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryIMPL(private val localDataSource: LocalDataSource) : LocalNewsRepository {
    override suspend fun saveNews(news: Result) {
        localDataSource.saveNews(news)
    }

    override fun getSavedNews(): Flow<List<Result>> {
        return localDataSource.getSavedNews()
    }

    override suspend fun deleteNews(news: Result) {
        localDataSource.deleteNews(news)
    }

    override suspend fun deleteNewsDyId(id: String) {
        localDataSource.deleteNewsDyId(id)
    }

    override fun getNewsById(id: String): Flow<Result> {
        return localDataSource.getNewsById(id)
    }
}