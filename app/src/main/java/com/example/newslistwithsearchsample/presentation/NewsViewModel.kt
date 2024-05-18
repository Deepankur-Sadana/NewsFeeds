package com.example.newslistwithsearchsample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newslistwithsearchsample.data.Article
import com.example.newslistwithsearchsample.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
): ViewModel() {
    fun getBreakingNews(): Flow<PagingData<Article>> = repository.getNews().cachedIn(viewModelScope)
}