package com.example.newslistwithsearchsample.di

import com.example.newslistwithsearchsample.data.NewsApiService
import com.example.newslistwithsearchsample.data.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideNewsRepository(newsApiService: NewsApiService): NewsRepository = NewsRepository(newsApiService)

}