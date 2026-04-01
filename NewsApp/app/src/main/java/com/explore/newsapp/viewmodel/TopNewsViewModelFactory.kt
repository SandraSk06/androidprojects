package com.explore.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.explore.newsapp.model.NewsRepository

@Suppress("UNCHECKED_CAST")
class TopNewsViewModelFactory(private val repo: NewsRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopNewsViewModel::class.java)){
            return TopNewsViewModel(repo) as T
        }
    throw IllegalArgumentException()
    }
}