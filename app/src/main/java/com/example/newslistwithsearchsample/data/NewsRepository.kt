package com.example.newslistwithsearchsample.data


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService,
    private val iPagerProvider: IPagerProvider,
) {
    fun getNews() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsApiService)
        }
    ).flow

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getQueriedNews(
        query: Flow<String>
    ) : Flow<PagingData<Article>> {
        val pagingDataFlow = query
            .flatMapLatest { path ->
                iPagerProvider.getPagedData(path)
        }
        return pagingDataFlow
    }
}