package com.explore.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.explore.newsapp.model.NewsApiData
import com.explore.newsapp.model.NewsRepository
import com.explore.newsapp.model.ResourceWrapper
import kotlinx.coroutines.launch

class TopNewsViewModel(val newsRepo: NewsRepository): ViewModel() {

    private val newsLiveData = MutableLiveData<ResourceWrapper<NewsApiData>>()
    val topNews: LiveData<ResourceWrapper<NewsApiData>> = newsLiveData

    fun fetchNews() {
        newsLiveData.value = ResourceWrapper.Loading()
        viewModelScope.launch {
            newsLiveData.value = newsRepo.fetchNews()
        }
    }
}