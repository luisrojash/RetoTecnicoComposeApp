package com.lerp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lerp.common.presentation.ContentState
import com.lerp.common.presentation.ScreenState
import com.lerp.presentation.component.BottomRegisterScreenPerson
import com.lerp.presentation.component.ItemPersonSelection

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    screenState: ScreenState<HomeScreenState>,
    onEvent: (HomeScreenEvent) -> Unit,
) {
    val state = screenState.content.content
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    Scaffold(topBar = {}) {
        BoxWithConstraints {
            val sheetHeight = this.constraints.maxHeight * 0.92f
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onEvent(HomeScreenEvent.HideBottomDialog)
                    }
            ) {
                ModalBottomSheetLayout(
                    sheetState = bottomSheetState,
                    sheetContent = {
                        BottomRegisterScreenPerson(
                            state = state,
                            sheetHeight = sheetHeight,
                            onEvent = onEvent
                        )
                    },
                    sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    sheetElevation = 8.dp,
                    modifier = Modifier.padding(it)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.White)
                                .padding(horizontal = 16.dp, vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            LazyColumn(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .testTag("LazyColumn"),
                            ) {
                                items(state.listUserUi) { person ->
                                    ItemPersonSelection(
                                        item = person,
                                        onClickReview = {
                                            onEvent(HomeScreenEvent.OnClickReviewItem(it))
                                        }
                                    )
                                }
                            }

                            Text(
                                text = "Agregar nuevo usuario",
                                modifier = Modifier.clickable {
                                    onEvent(HomeScreenEvent.OnAddNewUser)
                                },
                                color = Color.Blue,
                                textAlign = TextAlign.Center,
                                textDecoration = TextDecoration.Underline,
                                style = MaterialTheme.typography.body2.copy(
                                    fontSize = 14.sp, fontWeight = FontWeight(700)
                                )
                            )
                        }
                    }

                }

            }
        }
    }
    LaunchedEffect(bottomSheetState.isVisible) {
        if (!bottomSheetState.isVisible) {
            onEvent(HomeScreenEvent.HideBottomDialog)
        }
    }

    LaunchedEffect(state.showModalRegister) {
        if (state.showModalRegister) {
            bottomSheetState.show()
        } else {
            bottomSheetState.hide()
        }
    }
}


@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(
        screenState = ScreenState(
            content = ContentState(
                content = HomeScreenState(),
                isLoadingPage = false,
                isLoadingContent = false
            )
        ),
        onEvent = {}
    )
}