package com.example.newslistwithsearchsample.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.newslistwithsearchsample.domain.entity.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var response: StateFlow<Response<ArticleDetails>>

    init {
        val title = savedStateHandle.get<String>("title")
        val url = savedStateHandle.get<String>("url")
        val description = savedStateHandle.get<String>("description")

        val details = ArticleDetails(
            imageUrl = url.toString(),
            title = title.toString(),
            description = description.toString()
        )
        response = MutableStateFlow<Response<ArticleDetails>>(Response.Success(details))
    }


}
