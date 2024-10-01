package com.lerp.presentation

import com.lerp.domain.entities.UserUi

data class HomeScreenState(
    val listUserUi: MutableList<UserUi> = mutableListOf(),
    val showModalRegister: Boolean = false,
    val nameDialog:String = "",
    val typeUserDialog :String = ""
)