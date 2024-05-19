package com.example.newslistwithsearchsample.data

import com.example.newslistwithsearchsample.data.remote.NewsApiService
import com.example.newslistwithsearchsample.data.remote.NewsRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test


class NewsRepositoryTest {
    @Test
    fun `it can return pager flow without quey`() {
        val apiService = mock<NewsApiService>()
        val pagerProvider = mock<IPagerProvider>()
        val repo = NewsRepository(apiService, pagerProvider)
        val pagingData = repo.getNews()
        Truth.assertThat(pagingData).isNotNull()
    }

    @Test
    fun `it can return pager flow`() {
        val apiService = mock<NewsApiService>()
        val pagerProvider = mock<IPagerProvider>()
        val repo = NewsRepository(apiService, pagerProvider)
        val queryFlow = flow<String>{
        }
        val pagingData = repo.getQueriedNews(queryFlow)
        Truth.assertThat(pagingData).isNotNull()
    }


    @Test
    fun `when data is emitted into query, it can refresh it`() = runBlocking{
        val apiService = mock<NewsApiService>()
        val pagerProvider = mock<IPagerProvider>()
        val repo = NewsRepository(apiService, pagerProvider)
        val queryFlow = flow<String>{
            emit("query1")
            emit("query2")
            emit("query3")
        }
        val pagingData = repo.getQueriedNews(queryFlow)

        delay(1200)

        Truth.assertThat(pagingData).isNotNull()
    }


}