package com.example.feedapplication.data.repository

import com.example.feedapplication.data.model.NewsResponse
import com.example.feedapplication.data.repository.datasourse.RemoteDataSource
import com.example.feedapplication.domain.u.repository.remote.RemoteNewsRepository
import com.example.foodapp.util.Resource
import retrofit2.Response

class RemoteRepositoryIMPL(private val remoteDataSource: RemoteDataSource) : RemoteNewsRepository {
    override suspend fun getNews(api_key: String,page:Int): Resource<NewsResponse> {
        return responseToResultResourceNewsResponse(remoteDataSource.getNews(api_key,page))
    }

    private fun responseToResultResourceNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)

            }
        }
        return Resource.Error(response.message())
    }
}