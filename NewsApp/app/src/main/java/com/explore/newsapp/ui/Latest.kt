package com.explore.newsapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LatestScreen(modifier: Modifier) {
    Scaffold(modifier = modifier) { innerPadding ->
        LatestNews(Modifier.padding(innerPadding))
    }
}

@Composable
fun LatestNews(modifier: Modifier) {
    Text("Latest news", modifier)
}