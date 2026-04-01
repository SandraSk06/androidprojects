package com.explore.newsapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.explore.newsapp.model.NewsData
import com.explore.newsapp.model.NewsRepository
import com.explore.newsapp.model.ResourceWrapper
import com.explore.newsapp.viewmodel.TopNewsViewModel
import com.explore.newsapp.viewmodel.TopNewsViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PopularScreen(modifier: Modifier) {
    val repo = NewsRepository()
    val factory = TopNewsViewModelFactory(repo)
    val viewModel: TopNewsViewModel = viewModel(factory = factory)
    val newsModelState = viewModel.topNews.observeAsState()

    LaunchedEffect(Unit)  {
        viewModel.fetchNews()
    }

    when(val state = newsModelState.value) {
        is ResourceWrapper.Loading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        is ResourceWrapper.Success -> {
            Scaffold(modifier = modifier) {
                RecyclerView(state.data?.articles ?: emptyList())
            }
        }
        is ResourceWrapper.Error -> {
            Scaffold(modifier = modifier) {
                Text(text = state.message ?: "An unknown error occurred")
            }
        }
        else -> {
            Scaffold(modifier = modifier) {
                Text(text = "No data")
            }
        }
    }
}

@Composable
fun RecyclerView(dataList: List<NewsData>) {
    LazyColumn(modifier = Modifier.padding(5.dp)) {
        items(items=dataList) {
            NewsCard(it)
        }
    }
}

@Composable
fun NewsCard(data: NewsData) {
    Card(modifier = Modifier.padding(5.dp)) {
        PopularNews(Modifier.padding(3.dp), data.content)
    }
}

@Composable
fun PopularNews(modifier: Modifier, dataText: String?) {
    dataText?.takeIf { it.isNotEmpty() }?.let { Text(dataText, modifier = modifier) }
}