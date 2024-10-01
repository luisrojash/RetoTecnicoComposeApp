package com.lerp.designsystem

import androidx.compose.runtime.Composable
import com.lerp.common.presentation.ScreenState
import com.lerp.designsystem.dialogs.ProgressDialog

@Composable
fun <T> BaseScreen(
    state: ScreenState<T>,
    loadingContent: @Composable () -> Unit,
    onDialogDismiss: () -> Unit = {},
    content: @Composable () -> Unit
) {

    if (state.content.isLoadingContent) {
        loadingContent()
        //return
    }

    if (state.content.isLoadingPage) {
        ProgressDialog {}
    }

    state.content.content?.let {
        content()
    }
    state.dialog?.let { dialog ->
        /* CustomErrorDialog(
             titleError = dialog.title,
             messageError = dialog.description,
             onButtonClick = dialog.doOnAction,
             onDismiss = {
                 Log.d("BaseScreen", "onDismiss")
                 onDialogDismiss()
             }
         )*/
    }
}

