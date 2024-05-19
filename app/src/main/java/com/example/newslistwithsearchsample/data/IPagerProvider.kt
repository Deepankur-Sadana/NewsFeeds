package com.example.newslistwithsearchsample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IPagerProvider {
    fun getPagedData(path: String): Flow<PagingData<Article>>
}

class PagerProviderImpl
@Inject constructor(private val newsApiService: NewsApiService) :
    IPagerProvider {
    override fun getPagedData(path: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                NewsPagingSource(newsApiService, path)
            }
        ).flow
    }

}