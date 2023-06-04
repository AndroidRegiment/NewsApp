package com.androidregiment.newsapp.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.androidregiment.newsapp.navigation.SetUpNavGraph

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    SetUpNavGraph(navController = navController)
}