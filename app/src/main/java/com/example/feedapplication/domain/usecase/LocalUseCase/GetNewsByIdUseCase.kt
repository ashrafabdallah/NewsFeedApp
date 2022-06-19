package com.example.feedapplication.domain.usecase.LocalUseCase

import com.example.feedapplication.data.model.Result
import com.example.feedapplication.domain.repository.local.LocalNewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsByIdUseCase(private val localNewsRepository: LocalNewsRepository) {
    fun executeGetNewsById(id: String): Flow<Result> {
        return localNewsRepository.getNewsById(id)
    }
}