package com.lerp.designsystem.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun ProgressDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current

     Dialog(
         onDismissRequest = {
             onDismiss()
         },
         properties = DialogProperties(
             dismissOnBackPress = true,
             dismissOnClickOutside = true
         )
     ) {
         Box(
             modifier = Modifier.fillMaxSize(),
             contentAlignment = Alignment.Center
         ) {
             CircularProgressIndicator()
         }
     }

}


@Preview
@Composable
fun ProgressDialogPreview() {
    ProgressDialog {}
}