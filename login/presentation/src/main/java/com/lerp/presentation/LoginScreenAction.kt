package com.lerp.presentation


sealed interface LoginScreenAction {
    data object NavigateToHome : LoginScreenAction
}