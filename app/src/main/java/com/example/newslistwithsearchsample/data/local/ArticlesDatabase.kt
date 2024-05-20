package com.example.newslistwithsearchsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newslistwithsearchsample.data.entity.Article
import com.example.newslistwithsearchsample.data.entity.RemoteKeys

@Database(
    entities = [Article::class, RemoteKeys::class],
    version = 1,
)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}