package com.example.newslistwithsearchsample.presentation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.newslistwithsearchsample.data.entity.Article

//import androidx.paging.compose.items
@Composable
fun PagingListScreen() {
    val viewModel = hiltViewModel<NewsViewModel>()


  /*  LazyColumn {
        items(
            items = articles,
            key = { it.url }
        ) { article ->
            Text(
                modifier = Modifier
                    .height(75.dp),
                text = article.title ?: "",
            )

            Divider()
        }

        when (val state = articles.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                //TODO Error Item
                //state.error to get error message
            }
            is LoadState.Loading -> { // Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            else -> {}
        }

        when (val state = articles.loadState.append) { // Pagination
            is LoadState.Error -> {
                //TODO Pagination Error Item
                //state.error to get error message
            }
            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            else -> {}
        }
    }*/
}
//TODO("DS remove this
var i = 0
@Composable
fun ArticleListContent(
    articleLazyPagingItems: LazyPagingItems<Article>,
    navigateToDetail: (Article) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (articleLazyPagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if(articleLazyPagingItems.loadState.refresh is  LoadState.Error) {
            Text(
                modifier = Modifier
                    .height(75.dp),
                text = (articleLazyPagingItems.loadState.refresh as LoadState.Error).error.toString(),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = articleLazyPagingItems.itemCount,
                    key = articleLazyPagingItems.itemKey { ++i },
                ) { index ->
                    articleLazyPagingItems[index]?.let { article ->
                        NewsItem(
                            article,
                            onClick = {
                                navigateToDetail(article)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Divider(
                            color = MaterialTheme.colorScheme.secondary,
                            thickness = 0.2.dp,
                            modifier = Modifier.padding(horizontal = 20.dp),
                        )
                    }
                }
                item {
                    if (articleLazyPagingItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
