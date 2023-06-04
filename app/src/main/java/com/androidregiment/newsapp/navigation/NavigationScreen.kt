package com.androidregiment.newsapp.navigation

sealed class NavigationScreen(
    val route: String
) {
    object HomeScreen : NavigationScreen(
        route = "HOME_SCREEN"
    )

    object WebViewScreen : NavigationScreen(
        route = "WEB_VIEW_SCREEN"
    )

}
