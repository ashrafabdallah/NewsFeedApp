package com.example.feedapplication.presention.viewmodel.localviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.feedapplication.data.model.Result
import com.example.feedapplication.domain.usecase.LocalUseCase.DeleteNewsByIDUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.GetNewsByIdUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.GetSavedNewsUseCase
import com.example.feedapplication.domain.usecase.LocalUseCase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LocalViewModel(
    private val app: Application,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteNewsByIDUseCase: DeleteNewsByIDUseCase,
    private val getNewsByIdUseCase: GetNewsByIdUseCase
) :
    AndroidViewModel(app) {
    fun savedNews(news: Result) = viewModelScope.launch(Dispatchers.IO) {
        saveNewsUseCase.executeSaveNews(news)
    }

    fun getFavoritsFromDataBase() = liveData {
        getSavedNewsUseCase.executeGetSavedNews().collect {
            emit(it)
        }
    }


    fun deleteNewsById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        deleteNewsByIDUseCase.executeDeleteNewById(id)
    }

    fun getNewsById(id:String) = liveData {
        getNewsByIdUseCase.executeGetNewsById(id).collect{
            emit(it)
        }
    }
}