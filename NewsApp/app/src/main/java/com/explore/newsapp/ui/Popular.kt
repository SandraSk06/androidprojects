package com.explore.newsapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PopularScreen(modifier: Modifier) {
    Scaffold(modifier = modifier) {
        innerPadding -> PopularNews(Modifier.padding(innerPadding))
    }
}

@Composable
fun PopularNews(modifier: Modifier) {
    Text("Popular News", modifier = modifier)
}