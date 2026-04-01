package com.explore.newsapp.model

data class NewsApiData(
    var status: String,
    var articles: List<NewsData>
)
