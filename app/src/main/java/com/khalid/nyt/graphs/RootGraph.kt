package com.khalid.nyt.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.khalid.articles.presentation.view.ArticlesScreen
import com.khalid.articles.presentation.viewModel.ArticlesViewModel
import com.khalid.nyt.destinations.RootDestination

@Composable
fun RootGraph(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        startDestination = RootDestination.ArticlesScreen,
        modifier = modifier,
        navController = navController,
    ) {
        composable<RootDestination.ArticlesScreen> {
            val viewModel = hiltViewModel<ArticlesViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            ArticlesScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable<RootDestination.ArticleDetailsScreen> { navBackStackEntry ->
            //TODO: article details screen
        }
    }
}