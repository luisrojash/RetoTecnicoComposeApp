package com.lerp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun RetoNavHost(
    navController: NavHostController,
) {
    val startDestination = "/start_login"
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        mainGraph(navController,startDestination)

    }

}