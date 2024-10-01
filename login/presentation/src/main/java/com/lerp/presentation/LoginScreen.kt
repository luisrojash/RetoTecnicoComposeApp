package com.lerp.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lerp.common.presentation.ContentState
import com.lerp.common.presentation.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


@Composable
fun LoginScreen(
    navigateToStartHome: () -> Unit,
    screenState: ScreenState<LoginScreenState>,
    onEvent: (LoginScreenEvent) -> Unit,
    eventsFlow: SharedFlow<LoginScreenAction>
) {

    val state = screenState.content.content

    LaunchedEffect(key1 = Unit) {
        eventsFlow.collect { action ->
            when (action) {
                is LoginScreenAction.NavigateToHome -> navigateToStartHome()
            }
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            OutlinedTextField(
                value = state.textUser,
                onValueChange = { newText ->
                    onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Ingrese su usuario") },
                placeholder = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )
            OutlinedTextField(
                value = state.textPassword,
                onValueChange = { newText ->
                    onEvent(LoginScreenEvent.OnPasswordChanged(newText))
                },
                label = { Text("Ingrese su clave") },
                placeholder = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )
        }
        Button(
            onClick = {
                onEvent(LoginScreenEvent.OnClickSession)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesion")
        }

    }
}


@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(
        onEvent = {},
        navigateToStartHome = {},
        eventsFlow = MutableSharedFlow(replay = 0),
        screenState = ScreenState(
            content = ContentState(
                content = LoginScreenState(
                    textUser = "User",
                    textPassword = "Password"
                ),
                isLoadingPage = false,
                isLoadingContent = false
            )
        ),
    )

}