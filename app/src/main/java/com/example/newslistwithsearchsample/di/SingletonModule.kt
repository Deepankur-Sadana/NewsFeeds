package com.example.newslistwithsearchsample.di


import com.example.newslistwithsearchsample.data.entity.Article
import com.example.newslistwithsearchsample.data.remote.NewsApiService
import com.example.newslistwithsearchsample.data.entity.NewsResponse
import com.example.newslistwithsearchsample.data.entity.Source
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): NewsApiService {
        return FakeNewsAPIService()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NewsApiService::class.java)
    }
}

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