package com.example.feedapplication.data.db

import androidx.room.*
import com.example.feedapplication.data.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(news: Result)

    @Query("SELECT * FROM news_table")
    fun getSavedNews(): Flow<List<Result>>

    @Delete
    suspend fun deleteNews(news: Result)

    @Query("DELETE FROM news_table WHERE id =:id")
    suspend fun deleteNewsDyId(id: String)

    @Query("SELECT * FROM news_table WHERE id =:id")
    fun getNewsById(id: String): Flow<Result>

}