package com.explore.newsapp.model

import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("top-headlines?country=us&category=business&apiKey=cf25ce7ed18e48d2aaa00f695ac33c05")
    suspend fun getTopNews(): Response<NewsApiData>
}