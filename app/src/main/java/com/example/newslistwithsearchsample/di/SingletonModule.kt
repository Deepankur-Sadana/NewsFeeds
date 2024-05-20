package com.example.newslistwithsearchsample.di


import android.content.Context
import androidx.room.Room
import com.example.newslistwithsearchsample.data.local.ArticlesDatabase
import com.example.newslistwithsearchsample.data.local.ArticlesDao
import com.example.newslistwithsearchsample.data.local.RemoteKeysDao
import com.example.newslistwithsearchsample.data.remote.FakeNewsAPIService
import com.example.newslistwithsearchsample.data.remote.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
//        return FakeNewsAPIService()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): ArticlesDatabase =
        Room
            .databaseBuilder(context, ArticlesDatabase::class.java, "articles_database")
            .build()

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: ArticlesDatabase): ArticlesDao =
        moviesDatabase.getMoviesDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(
        moviesDatabase: ArticlesDatabase
    ): RemoteKeysDao = moviesDatabase.getRemoteKeysDao()
}

