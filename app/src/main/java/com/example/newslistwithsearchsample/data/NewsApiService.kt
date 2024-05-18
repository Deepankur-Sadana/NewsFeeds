package com.example.newslistwithsearchsample.data

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    //TODO ds change back page size
    // move keys to constants
    @GET("top-headlines?q=us&apiKey=643734f7296a4560a2528084aa5b326f&pageSize=20")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("q") query: String?
    ): NewsResponse
    @GET("everything?apiKey=643734f7296a4560a2528084aa5b326f&pageSize=20")
    suspend fun getSearchedNews(
        @Query("page") page: Int,
        @Query("q") query: String?
    ) :NewsResponse
}