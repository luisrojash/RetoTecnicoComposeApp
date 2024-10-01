package com.lerp.common.presentation

data class ScreenState<T>(
    val content: ContentState<T>,
    val dialog: DialogState? = null
)

data class ContentState<T>(
    val content: T,
    val isLoadingPage: Boolean,
    val isLoadingContent :Boolean
)

data class DialogState(
    val icon: Int,
    val title: String,
    val description: String,
    val actionText: String,
    val doOnAction: () -> Unit,
    val doOnDismiss: () -> Unit
)