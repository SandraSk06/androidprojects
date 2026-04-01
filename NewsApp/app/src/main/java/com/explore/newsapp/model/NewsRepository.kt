package com.explore.newsapp.model

class NewsRepository {
    suspend fun fetchNews(): ResourceWrapper<NewsApiData> {
        return try {
            val response = RetrofitInstance.api.getTopNews()
            if (response.isSuccessful) {
                response.body()
                    ?.let { ResourceWrapper.Success(it) }
                    ?: ResourceWrapper.Error("Response body is null")
            } else {
                ResourceWrapper.Error("Error: ${response.code()}, message: ${response.message()}")
            }
        } catch (e: Exception) {
            ResourceWrapper.Error("Failed to fetch data: ${e.message}")
        }
    }
}