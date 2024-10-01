package com.lerp.presentation.navigation


import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lerp.presentation.LoginScreen
import com.lerp.presentation.LoginViewModel

class LoginNavigation {
    companion object {
        const val route: String = "/screen_login"
        fun getRouteToNavigate() = route
    }
}


fun NavGraphBuilder.loginScreen(
    navigateToStartHome: () -> Unit
) {

    composable(LoginNavigation.route) {
        val viewModel = hiltViewModel<LoginViewModel>()
        val screenState = viewModel.screenState.collectAsStateWithLifecycle().value

        LoginScreen(
            onEvent = viewModel::onEvent,
            screenState = screenState,
            navigateToStartHome = navigateToStartHome,
            eventsFlow = viewModel.action,
        )
    }

}