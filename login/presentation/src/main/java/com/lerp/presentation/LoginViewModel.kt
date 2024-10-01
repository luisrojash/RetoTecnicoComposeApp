package com.lerp.presentation

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.lerp.common.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    resources: Resources,
) : BaseViewModel<LoginScreenState, LoginScreenEvent>(resources) {


    private val _actionFlow = MutableSharedFlow<LoginScreenAction>(replay = 0)
    val action: SharedFlow<LoginScreenAction> = _actionFlow

    override fun initialContent() = LoginScreenState()

    override fun initialLoadingPage(): Boolean = true

    override fun initialLoadingContent(): Boolean = false

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnClickSession -> onClickSession()
            is LoginScreenEvent.OnUserChanged -> onUserChanged(event)
            is LoginScreenEvent.OnPasswordChanged -> onPasswordChangedEvent(event)
        }
    }

    private fun onPasswordChangedEvent(event: LoginScreenEvent.OnPasswordChanged) {
        showContent(
            content = content.copy(
                textPassword = event.password
            )
        )
    }

    private fun onUserChanged(event: LoginScreenEvent.OnUserChanged) {
        showContent(
            content = content.copy(
                textUser = event.user
            )
        )
    }

    private fun onClickSession() {
        hidePageLoading()
        sendAction(LoginScreenAction.NavigateToHome)
    }

    private fun sendAction(action: LoginScreenAction) {
        viewModelScope.launch {
            _actionFlow.emit(action)
        }
    }


}