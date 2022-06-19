package com.example.feedapplication.data.repository.datasourse

import com.example.feedapplication.data.model.NewsResponse
import com.example.foodapp.util.Resource
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getNews(api_key: String,page:Int): Response<NewsResponse>
}