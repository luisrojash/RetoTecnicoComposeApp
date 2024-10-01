package com.lerp.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.lerp.presentation.navigation.HomeNavigation
import com.lerp.presentation.navigation.LoginNavigation
import com.lerp.presentation.navigation.homeScreen
import com.lerp.presentation.navigation.loginScreen


fun NavGraphBuilder.mainGraph(navController: NavController, startDestination: String) {
    navigation(
        route =startDestination,
        startDestination = LoginNavigation.getRouteToNavigate(),
    ) {
        loginScreen(
            navigateToStartHome = {
                /*Inicia el Start Home*/
                Log.i("MyNavGraph", "initStartToHome")
                navController.navigate(HomeNavigation.getRouteToNavigate())
            }
        )

        homeScreen()
    }
}


