package com.example.newslistwithsearchsample.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newslistwithsearchsample.data.entity.Article

@Composable
fun NewsItem(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = article.urlToImage,
            modifier = Modifier.size(60.dp),
            contentScale = ContentScale.Fit,
            contentDescription = null,
        )
        Text(
            "#${article.author} -",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
        )
    }
}

