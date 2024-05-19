package com.example.newslistwithsearchsample.data.remote

import com.example.newslistwithsearchsample.data.entity.Article
import com.example.newslistwithsearchsample.data.entity.NewsResponse
import com.example.newslistwithsearchsample.data.entity.Source
import com.example.newslistwithsearchsample.data.remote.NewsApiService

class FakeNewsAPIService : NewsApiService {
    override suspend fun getNews(page: Int): NewsResponse {
        return NewsResponse(getArticles(), "", 20)
    }

    override suspend fun getSearchedNews(page: Int, query: String?): NewsResponse {
        return NewsResponse(getArticles(), "", 20)
    }

    private fun getArticles(): List<Article> {
//        throw Exception("Run time error")

        val articles = ArrayList<Article>()
        for (i in 1..20) {
            val a = Article(
                author = "author $i",
                content = "content $i",
                description = "description $i",
                publishedAt = "publishedAt $i",
                source = Source("", ""),
                title = "title $i",
                url = "",
                urlToImage = "https://fastly.picsum.photos/id/146/536/354.jpg?hmac=4P0o1OZvYRJq_jIExQUpqq4kQzW518ORptvq2blN-qU"
            )
            articles.add(a)
        }
        return articles
    }

}