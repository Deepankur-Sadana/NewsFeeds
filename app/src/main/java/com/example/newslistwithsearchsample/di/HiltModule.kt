package com.example.newslistwithsearchsample.di

import android.content.Context
import androidx.room.Room
import com.example.newslistwithsearchsample.data.IPagerProvider
import com.example.newslistwithsearchsample.data.remote.NewsApiService
import com.example.newslistwithsearchsample.data.remote.NewsRepository
import com.example.newslistwithsearchsample.data.PagerProviderImpl
import com.example.newslistwithsearchsample.data.local.ArticlesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideNewsRepository(
        newsApiService: NewsApiService,
        iPagerProvider: IPagerProvider,
    ): NewsRepository = NewsRepository(newsApiService, iPagerProvider)

    @Provides
    fun providesPager(newsApiService: NewsApiService, database: ArticlesDatabase) : IPagerProvider {
        return PagerProviderImpl(newsApiService, database)
    }


}