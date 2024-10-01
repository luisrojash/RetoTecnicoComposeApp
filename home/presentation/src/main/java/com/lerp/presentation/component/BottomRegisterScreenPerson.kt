package com.lerp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lerp.presentation.HomeScreenEvent
import com.lerp.presentation.HomeScreenState

@Composable
fun BottomRegisterScreenPerson(
    state: HomeScreenState,
    sheetHeight: Float,
    onEvent: (HomeScreenEvent) -> Unit
) {


    Box(
        modifier = Modifier
            .background(Color.White)
            .height(with(LocalDensity.current) {
                sheetHeight.toDp()
            })
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Registro Usuarios",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 20.dp,
                        top = 24.dp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.nameDialog,
                    onValueChange = { newText ->
                        onEvent(HomeScreenEvent.OnDialogUserName(newText))
                    },
                    label = { Text("Ingrese el Nombre") },
                    placeholder = { Text("Nombre") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.typeUserDialog,
                    onValueChange = { newText ->
                        onEvent(HomeScreenEvent.OnDialogTypeName(newText))
                    },
                    label = { Text("Tipo de Usuario") },
                    placeholder = { Text("Nombre") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true
                )

                Button(
                    onClick = {
                        onEvent(HomeScreenEvent.OnSaveUserDialog)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar")
                }
            }
        }

    }
}

@Composable
@Preview
fun BottomRegisterScreenPersonPreview() {
    BottomRegisterScreenPerson(
        state = HomeScreenState(),
        sheetHeight = 1500.0f,
        onEvent = {})
}