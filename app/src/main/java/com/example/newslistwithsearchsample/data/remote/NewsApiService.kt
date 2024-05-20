package com.example.newslistwithsearchsample.data.remote

import com.example.newslistwithsearchsample.data.entity.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
//TODO ds move keys to constants
const val API_KEY = "bec9e15c34b84630a108baa54f09be9c"
//const val API_KEY = "643734f7296a4560a2528084aa5b326f"
interface NewsApiService {
    @GET("top-headlines?q=us&apiKey=$API_KEY&pageSize=20")
    suspend fun getNews(
        @Query("page") page: Int
    ): NewsResponse


    @GET("everything?apiKey=${API_KEY}f&pageSize=20")
    suspend fun getSearchedNews(
        @Query("page") page: Int,
        @Query("q") query: String?
    ) : NewsResponse
}

//https://newsapi.org/v2/top-headlines?country=us&apiKey=bec9e15c34b84630a108baa54f09be9c&pageSize=20
//https://newsapi.org/v2/everything?apiKey=bec9e15c34b84630a108baa54f09be9cf&pageSize=20&page=1&q=asdf