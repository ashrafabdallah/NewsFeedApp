package com.example.feedapplication.presention.viewmodel.Homeviewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feedapplication.domain.usecase.RemoteUseCase.NewsFeedUseCase

class HomeViewModelFactory(
    private val app: Application,
    private val newsFeedUseCase: NewsFeedUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(newsFeedUseCase, app) as T
        }
        throw IllegalAccessException("Unknown View Model Class ......")
    }
}