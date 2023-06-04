package com.androidregiment.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidregiment.newsapp.screen.home.HomeScreen
import com.androidregiment.newsapp.screen.webview.WebViewScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.HomeScreen.route
    ) {

        composable(route = NavigationScreen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = "${NavigationScreen.WebViewScreen.route}/{url}" ,
            arguments = listOf(navArgument("url") { type = NavType.StringType })) {backStackEntry->
            backStackEntry.arguments?.getString("url")?.let {
                WebViewScreen(
                    it
                )
            }
        }

    }
}