package com.example.newslistwithsearchsample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newslistwithsearchsample.data.entity.Article
import com.example.newslistwithsearchsample.data.remote.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
): ViewModel() {

    private val _currentQuery = MutableStateFlow("")
    fun notifyTextChanged(it: String) {
        _currentQuery.value = it
    }

    @OptIn(FlowPreview::class)
    fun queryLatestNews(): Flow<PagingData<Article>> {
        return repository
            .getQueriedNews(_currentQuery.debounce(1000))
            .cachedIn(viewModelScope)
    }
}