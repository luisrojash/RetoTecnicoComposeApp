package com.lerp.presentation

sealed class LoginScreenEvent {
    data object OnClickSession : LoginScreenEvent()
    data class OnUserChanged(val user: String) : LoginScreenEvent()
    data class OnPasswordChanged(val password: String) : LoginScreenEvent()
}