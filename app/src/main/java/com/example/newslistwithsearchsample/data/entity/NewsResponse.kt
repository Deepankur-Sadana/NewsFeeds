package com.example.newslistwithsearchsample.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID




data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)


data class Source(
    val id: String,
    val name: String
)
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = false)
    val id: String = getUUID(),
    @ColumnInfo(name = "author")
    val author: String,
    val content: String,
    @ColumnInfo(name = "description")
    val description: String,
    val publishedAt: String,
    val source: Source,
    @ColumnInfo(name = "title")
    val title: String,
    val url: String,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String
)

fun getUUID(): String {
    val uuid = UUID.randomUUID()
    val uuidAsString = uuid.toString()
   return uuidAsString
}