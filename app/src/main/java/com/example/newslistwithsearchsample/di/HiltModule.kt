package com.example.newslistwithsearchsample.di

import com.example.newslistwithsearchsample.data.IPagerProvider
import com.example.newslistwithsearchsample.data.remote.NewsApiService
import com.example.newslistwithsearchsample.data.remote.NewsRepository
import com.example.newslistwithsearchsample.data.PagerProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideNewsRepository(
        newsApiService: NewsApiService,
        iPagerProvider: IPagerProvider,
    ): NewsRepository = NewsRepository(newsApiService, iPagerProvider)

    @Provides
    fun providesPager(newsApiService: NewsApiService) : IPagerProvider {
        return PagerProviderImpl(newsApiService)
    }
}