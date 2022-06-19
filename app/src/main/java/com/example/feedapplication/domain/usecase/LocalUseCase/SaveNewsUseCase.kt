package com.example.feedapplication.domain.usecase.LocalUseCase

import com.example.feedapplication.data.model.Result
import com.example.feedapplication.domain.repository.local.LocalNewsRepository

class SaveNewsUseCase(private val localNewsRepository: LocalNewsRepository) {
    suspend fun executeSaveNews(news: Result) {
        localNewsRepository.saveNews(news)
    }
}