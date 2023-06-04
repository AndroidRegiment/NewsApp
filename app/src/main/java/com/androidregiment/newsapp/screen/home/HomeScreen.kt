package com.androidregiment.newsapp.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.androidregiment.newsapp.data.Category
import com.androidregiment.newsapp.data.categoryList
import com.androidregiment.newsapp.model.Article
import com.androidregiment.newsapp.navigation.NavigationScreen
import com.androidregiment.newsapp.screen.commom.CategoryButton
import com.androidregiment.newsapp.screen.commom.ErrorScreen
import com.androidregiment.newsapp.screen.commom.LoadingScreen
import com.androidregiment.newsapp.screen.commom.NewsFeedItem
import com.androidregiment.newsapp.utils.ResultState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    val newsResult by viewModel.resultFlow.collectAsState()

    when (newsResult) {
        ResultState.SUCCESS -> {
            NewsScreen(
                _category = viewModel.categoryFlow,
                _articles = viewModel.articleFlow,
                onCategorySelected = viewModel::getNewsByCategory,
                navController = navController
            )
        }
        ResultState.LOADING -> {
            LoadingScreen()
        }
        ResultState.ERROR -> {
            ErrorScreen(
                onRetryClick = viewModel::retry
            )
        }
    }
}

@Composable
fun NewsScreen(
    _category: StateFlow<Category>,
    _articles: StateFlow<List<Article>>,
    onCategorySelected: (category: Category) -> Unit,
    navController: NavController,
) {
    val category = _category.collectAsState()
    val articles = _articles.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(Modifier.fillMaxWidth()) {
            items(categoryList) {
                CategoryButton(
                    category = it,
                    currentCategory = { category.value },
                    onCategorySelected = { category ->
                        onCategorySelected(category)
                    }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(articles.value) {
                NewsFeedItem(article = it,
                    onNewsItemClick = { url ->
                        navController.navigate("${NavigationScreen.WebViewScreen.route}/$url")
                    }
                )
            }
        }
    }
}