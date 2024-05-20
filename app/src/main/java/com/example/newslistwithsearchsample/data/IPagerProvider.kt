package com.example.newslistwithsearchsample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newslistwithsearchsample.data.entity.Article
import com.example.newslistwithsearchsample.data.local.ArticlesDatabase
import com.example.newslistwithsearchsample.data.local.ArticlesRemoteMediator
import com.example.newslistwithsearchsample.data.remote.NewsApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IPagerProvider {
    fun getPagedData(path: String): Flow<PagingData<Article>>
}

class PagerProviderImpl
@Inject constructor(
    private val newsApiService: NewsApiService,
    private val articlesDatabase: ArticlesDatabase,

    ) :
    IPagerProvider {
    override fun getPagedData(path: String): Flow<PagingData<Article>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 20,
//            ),
//            pagingSourceFactory = {
//                NewsPagingSource(newsApiService, path)
//            }
//        ).flow

        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                // The pagingSourceFactory lambda should always return a brand new PagingSource
                // when invoked as PagingSource instances are not reusable.
                articlesDatabase.getMoviesDao().getMovies()
            },
            remoteMediator = ArticlesRemoteMediator(
                newsApiService,
                articlesDatabase,
                path
            )
        ).flow
    }

}