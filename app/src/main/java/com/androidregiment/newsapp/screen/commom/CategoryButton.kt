package com.androidregiment.newsapp.screen.commom

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidregiment.newsapp.data.Category

@Composable
fun CategoryButton(
    category: Category,
    currentCategory: () -> Category,
    onCategorySelected: (category: Category) -> Unit

) {

    val backgroundColor by animateColorAsState(
        targetValue = if (currentCategory() == category) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.surface
    )
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clickable {
                onCategorySelected(category)
            },
        shape = RoundedCornerShape(percent = 50),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primaryVariant),
        backgroundColor = backgroundColor
    ) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            Icon(imageVector = category.icon, contentDescription = null)
            Text(text = category.strValue, modifier = Modifier)
        }

    }
}