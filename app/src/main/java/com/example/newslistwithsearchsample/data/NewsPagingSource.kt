package com.example.newslistwithsearchsample.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class NewsPagingSource (
    private val newsApiService: NewsApiService,
    private val query : String = ""
): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val res = state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
        Log.d("NewsPagingSource" ,"getRefreshKey res $res")
        return res
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response =
                if(query.isNullOrEmpty())
                    newsApiService.getNews(page = page)
            else
                newsApiService.getSearchedNews(page = page , query)
            Log.d("NewsPagingSource","$response")

            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}