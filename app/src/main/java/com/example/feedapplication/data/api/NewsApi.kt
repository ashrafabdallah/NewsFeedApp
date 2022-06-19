package com.example.feedapplication.data.api


import com.example.feedapplication.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/search")
    suspend fun getNews(@Query("api-key") apikey: String,@Query("page")page:Int): Response<NewsResponse>
}