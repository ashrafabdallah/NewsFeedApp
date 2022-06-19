package com.example.feedapplication.presention.viewmodel.localviewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feedapplication.domain.usecase.LocalUseCase.DeleteNewsByIDUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.GetNewsByIdUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.GetSavedNewsUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.SaveNewsUseCase

class LocalViewModelFactory(
    private val app: Application,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteNewsByIDUseCase: DeleteNewsByIDUseCase,
    private val getNewsByIdUseCase: GetNewsByIdUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocalViewModel::class.java)) {
            return LocalViewModel(app, saveNewsUseCase,getSavedNewsUseCase,deleteNewsByIDUseCase,getNewsByIdUseCase) as T
        }
        throw IllegalAccessException("Unknown View Model Class ......")
    }
}

