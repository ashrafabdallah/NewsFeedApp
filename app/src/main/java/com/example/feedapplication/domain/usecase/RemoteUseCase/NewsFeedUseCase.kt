package com.example.feedapplication.domain.usecase.RemoteUseCase

import com.example.feedapplication.data.model.NewsResponse
import com.example.feedapplication.domain.u.repository.remote.RemoteNewsRepository
import com.example.foodapp.util.Resource

class NewsFeedUseCase(private val remoteNewsRepository: RemoteNewsRepository) {
    suspend fun executeGetNews(api_key: String,page:Int): Resource<NewsResponse> {
        return remoteNewsRepository.getNews(api_key,page)
    }
}