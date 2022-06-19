package com.example.feedapplication.domain.repository.local

import com.example.feedapplication.data.model.Result
import kotlinx.coroutines.flow.Flow

interface LocalNewsRepository {

    suspend fun saveNews(news: Result)
    fun getSavedNews(): Flow<List<Result>>
    suspend fun deleteNews(news: Result)
    suspend fun deleteNewsDyId(id:String)
    fun getNewsById(id: String): Flow<Result>
}