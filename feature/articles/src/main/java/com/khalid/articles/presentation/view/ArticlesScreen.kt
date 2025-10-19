package com.khalid.articles.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.khalid.articles.presentation.utils.ArticlesScreenEvent
import com.khalid.articles.presentation.utils.ArticlesScreenState
import com.khalid.common.utils.ifNotNull
import com.khalid.common.utils.ifTrue

@Composable
fun ArticlesScreen(
    state: ArticlesScreenState,
    onEvent: (ArticlesScreenEvent) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        state.isLoading.ifTrue {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                )
            }
        }

        state.errorMessage.ifNotNull {
            item {
                Text(it)
            }
        }

        items(state.articles) { article ->
            ArticleCard(
                article = article,
                modifier = Modifier.padding(horizontal = 12.dp).height(200.dp)
            )
        }
    }
}