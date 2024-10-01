package com.lerp.presentation

import com.lerp.domain.entities.UserUi

sealed class HomeScreenEvent {
    data object OnAddNewUser : HomeScreenEvent()
    data object HideBottomDialog: HomeScreenEvent()

    data class OnDialogUserName(val name: String) : HomeScreenEvent()
    data class OnDialogTypeName(val type: String) : HomeScreenEvent()
    data object OnSaveUserDialog: HomeScreenEvent()


    data class OnClickReviewItem(val person: UserUi) : HomeScreenEvent()


}

