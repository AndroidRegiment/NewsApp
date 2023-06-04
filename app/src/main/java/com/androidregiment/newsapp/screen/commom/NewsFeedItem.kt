package com.androidregiment.newsapp.screen.commom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.androidregiment.newsapp.model.Article
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsFeedItem(
    article: Article,
    onNewsItemClick: (url: String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                onNewsItemClick(encodedUrl)
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null, modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop

            )

            Text(
                text = article.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }

}