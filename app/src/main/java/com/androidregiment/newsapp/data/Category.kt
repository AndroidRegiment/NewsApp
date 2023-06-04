package com.androidregiment.newsapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class Category(val icon: ImageVector, val strValue: String) {
    ALL(icon = Icons.Default.Newspaper, strValue = ""),
    BUSINESS(icon = Icons.Default.CorporateFare, strValue = "business"),
    ENTERTAINMENT(icon = Icons.Default.Smartphone, strValue = "entertainment"),
    GENERAL(icon = Icons.Default.Public, strValue = "general"),
    HEALTH(icon = Icons.Default.HealthAndSafety, strValue = "health"),
    SCIENCE(icon = Icons.Default.Science, strValue = "science"),
    SPORTS(icon = Icons.Default.SportsVolleyball, strValue = "sports"),
    TECHNOLOGY(icon = Icons.Default.Computer, strValue = "technology"),
}

val categoryList = listOf(
    Category.ALL,
    Category.BUSINESS,
    Category.ENTERTAINMENT,
    Category.GENERAL,
    Category.HEALTH,
    Category.SCIENCE,
    Category.SPORTS,
    Category.TECHNOLOGY
)