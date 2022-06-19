package com.example.feedapplication.domain.u.repository.remote

import com.example.feedapplication.data.model.NewsResponse
import com.example.foodapp.util.Resource
import retrofit2.Response

interface RemoteNewsRepository {
    suspend fun getNews(api_key: String,page:Int): Resource<NewsResponse>
}
