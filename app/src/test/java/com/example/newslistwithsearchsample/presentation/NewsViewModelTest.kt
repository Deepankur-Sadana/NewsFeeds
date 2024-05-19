package com.example.newslistwithsearchsample.presentation

import androidx.paging.PagingData
import com.example.newslistwithsearchsample.data.Article
import com.example.newslistwithsearchsample.data.NewsRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class NewsViewModelTest {
    @Test
    fun `it can fetch data from Repo` () {
        //given
        val repo = mock<NewsRepository>()
        val newsViewModel = NewsViewModel(repo)

        val flow = flow<PagingData<Article>> {
        }

        whenever(repo
            .getQueriedNews(any())).thenReturn(flow)
        //when
        newsViewModel.queryLatestNews()

        //then
        verify(repo).getQueriedNews(any())
    }

    @Test
    fun `when multiple requests are made on query, then fetching happens only on last query` () {
        //given
        val repo = mock<NewsRepository>()
        val newsViewModel = NewsViewModel(repo)

        val flow = flow<PagingData<Article>> {
        }

        whenever(repo
            .getQueriedNews(any())).thenReturn(flow)
        //when
        newsViewModel.queryLatestNews()
        newsViewModel.notifyTextChanged("text1")
        newsViewModel.notifyTextChanged("text2")

        //then
        verify(repo, times(1)).getQueriedNews(any())
    }

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}