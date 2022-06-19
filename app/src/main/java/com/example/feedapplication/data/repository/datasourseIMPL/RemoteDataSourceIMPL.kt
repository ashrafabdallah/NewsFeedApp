package com.example.feedapplication.data.repository.datasourseIMPL

import com.example.feedapplication.data.api.NewsApi
import com.example.feedapplication.data.model.NewsResponse
import com.example.feedapplication.data.repository.datasourse.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceIMPL(private val newsApi: NewsApi) : RemoteDataSource {
    override suspend fun getNews(api_key: String,page:Int): Response<NewsResponse> {
        return newsApi.getNews(api_key,page)
    }
}