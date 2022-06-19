package com.example.feedapplication.presention.viewmodel.Homeviewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feedapplication.data.model.NewsResponse
import com.example.feedapplication.domain.usecase.RemoteUseCase.NewsFeedUseCase
import com.example.foodapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val newsFeedUseCase: NewsFeedUseCase, private val app: Application):AndroidViewModel(app) {



    var connectData: MutableLiveData<String> = MutableLiveData()
    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    fun getConnect() {
        if (isNetworkAvailable(app)) {
            connectData.postValue("ok")
        } else {
            connectData.postValue("no")
        }
    }

    var newsResponse: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    fun getNews(page:Int) = viewModelScope.launch(Dispatchers.IO)
    {
        newsResponse.postValue(Resource.Loading())
        try {
            if (connectData.value == "ok") {
                var response = newsFeedUseCase.executeGetNews("64fced3c-ee9e-4661-9156-2ea8417b481b",page)
                newsResponse.postValue(response)
            } else {
                newsResponse.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            Log.i("TERROR",e.message.toString())
            newsResponse.postValue(Resource.Error(e.message.toString()))
        }

    }


}