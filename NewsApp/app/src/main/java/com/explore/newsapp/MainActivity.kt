package com.explore.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.explore.newsapp.ui.FavoritesScreen
import com.explore.newsapp.ui.LatestScreen
import com.explore.newsapp.ui.PopularScreen
import com.explore.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                NewsAppApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun NewsAppApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.POPULAR) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize().padding(5.dp,0.dp, 5.dp,0.dp)) { innerPadding ->
            when (currentDestination) {
                AppDestinations.POPULAR -> PopularScreen(
                    modifier = Modifier.padding(innerPadding)
                )
                AppDestinations.LATEST -> LatestScreen(
                    modifier = Modifier.padding(innerPadding)
                )
                AppDestinations.FAVORITES -> FavoritesScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    POPULAR("Popular", R.drawable.ic_home),
    LATEST("Latest", R.drawable.ic_favorite),
    FAVORITES("Favorites", R.drawable.ic_account_box),
}