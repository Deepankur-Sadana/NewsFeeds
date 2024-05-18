package com.example.newslistwithsearchsample.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.newslistwithsearchsample.domain.entity.Response

@Composable
fun ArticleDetailScreen(snackbarHostState: SnackbarHostState) {
    val viewModel = hiltViewModel<ArticleDetailViewModel>()
    val articleDetailsResponse by viewModel.response.collectAsState()

    if (articleDetailsResponse is Response.Error) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar((articleDetailsResponse as Response.Error).error)
        }
    }

    ArticleDetailContent(articleDetailsResponse = articleDetailsResponse)
}

@Composable
fun ArticleDetailContent(articleDetailsResponse: Response<ArticleDetails>) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (articleDetailsResponse.data != null) {
            val article = articleDetailsResponse.data!!

            Row(modifier = Modifier.padding(20.dp)) {
                AsyncImage(
                    model = article.imageUrl,
                    modifier = Modifier.width(500.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        article.title,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                    }
                }
            }
        }
        if (articleDetailsResponse is Response.Loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}
