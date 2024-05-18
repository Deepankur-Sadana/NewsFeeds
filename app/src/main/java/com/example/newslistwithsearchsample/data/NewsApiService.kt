package com.example.newslistwithsearchsample.data

import android.provider.SyncStateContract
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    //TODO ds change back page size
    @GET("everything?q=apple&sortBy=popularity&apiKey=643734f7296a4560a2528084aa5b326f&pageSize=4")
    suspend fun getNews(
        @Query("page") page: Int
    ): NewsResponse
}