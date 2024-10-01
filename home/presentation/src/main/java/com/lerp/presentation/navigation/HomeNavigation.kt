package com.lerp.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lerp.designsystem.BaseScreen
import com.lerp.presentation.HomeScreen
import com.lerp.presentation.HomeViewModel


class HomeNavigation {
    companion object {
        const val route: String = "/screen_home"
        fun getRouteToNavigate() = route
    }
}

fun NavGraphBuilder.homeScreen(
) {

    composable(HomeNavigation.route) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val screenState = viewModel.screenState.collectAsStateWithLifecycle().value

        BaseScreen(state = screenState,
            loadingContent = {  }) {
            HomeScreen(
                onEvent = viewModel::onEvent,
                screenState = screenState
            )
        }


    }

}