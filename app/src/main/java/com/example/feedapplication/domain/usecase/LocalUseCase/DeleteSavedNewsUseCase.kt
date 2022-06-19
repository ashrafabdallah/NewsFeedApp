package com.example.feedapplication.domain.usecase.LocalUseCase

import com.example.feedapplication.data.model.Result
import com.example.feedapplication.domain.repository.local.LocalNewsRepository

class DeleteSavedNewsUseCase(private val localNewsRepository: LocalNewsRepository) {
    suspend fun executeDeleteSavedNews(news: Result) {
        localNewsRepository.deleteNews(news)
    }
}