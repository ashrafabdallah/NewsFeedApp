package com.example.feedapplication.domain.usecase.LocalUseCase

import com.example.feedapplication.data.model.Result
import com.example.feedapplication.domain.repository.local.LocalNewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val localNewsRepository: LocalNewsRepository) {
    fun executeGetSavedNews(): Flow<List<Result>>{
        return localNewsRepository.getSavedNews()
    }
}