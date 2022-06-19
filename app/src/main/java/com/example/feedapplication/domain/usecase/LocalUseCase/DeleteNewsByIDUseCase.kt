package com.example.feedapplication.domain.usecase.LocalUseCase

import com.example.feedapplication.domain.repository.local.LocalNewsRepository

class DeleteNewsByIDUseCase(private val localNewsRepository: LocalNewsRepository) {
    suspend fun executeDeleteNewById(id: String) {
        localNewsRepository.deleteNewsDyId(id)
    }
}