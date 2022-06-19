package com.example.feedapplication.data.repository.datasourseIMPL

import com.example.feedapplication.data.db.NewsDao
import com.example.feedapplication.data.model.Result
import com.example.feedapplication.data.repository.datasourse.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourseIMPL(private val dao: NewsDao) : LocalDataSource {
    override suspend fun saveNews(news: Result) {
        dao.saveNews(news)
    }

    override fun getSavedNews(): Flow<List<Result>> {
        return dao.getSavedNews()
    }

    override suspend fun deleteNews(news: Result) {
        dao.deleteNews(news)
    }

    override suspend fun deleteNewsDyId(id: String) {
        dao.deleteNewsDyId(id)
    }

    override fun getNewsById(id: String): Flow<Result> {
        return dao.getNewsById(id)
    }
}