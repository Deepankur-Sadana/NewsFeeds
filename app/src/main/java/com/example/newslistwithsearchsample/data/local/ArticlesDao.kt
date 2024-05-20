package com.example.newslistwithsearchsample.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newslistwithsearchsample.data.entity.Article

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Query("Select * From articles Order By page")
    fun getArticles(): PagingSource<Int, Article>

    @Query("Delete From articles")
    suspend fun clearAllArticles()
}