package com.example.feedapplication.data.repository.datasourse

import com.example.feedapplication.data.model.Result
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveNews(news: Result)
    fun getSavedNews(): Flow<List<Result>>
    suspend fun deleteNews(news: Result)
    suspend fun deleteNewsDyId(id:String)
    fun getNewsById(id: String): Flow<Result>

}