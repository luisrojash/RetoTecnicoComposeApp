package com.lerp.presentation

import android.content.res.Resources
import android.util.Log
import com.lerp.common.executeTask
import com.lerp.common.presentation.BaseViewModel
import com.lerp.domain.Failure
import com.lerp.domain.entities.UserUi
import com.lerp.domain.usecase.GetListUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    resources: Resources,
    val getListUserUseCase: GetListUserUseCase,
) : BaseViewModel<HomeScreenState, HomeScreenEvent>(resources) {

    private var typeScreen = 0
    private var positionOld = -1

    companion object {
        const val type_state_add = 0
        const val type_state_update = 1
    }

    init {
        getListUser()
    }


    override fun initialContent() = HomeScreenState()

    override fun initialLoadingPage(): Boolean = true

    override fun initialLoadingContent(): Boolean = false

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.HideBottomDialog -> hideBottomDialog()
            is HomeScreenEvent.OnAddNewUser -> onAddNewUser()
            is HomeScreenEvent.OnSaveUserDialog -> onSaveUserDialog()
            is HomeScreenEvent.OnDialogUserName -> onDialogUserName(event.name)
            is HomeScreenEvent.OnDialogTypeName -> onDialogTypeName(event.type)
            is HomeScreenEvent.OnClickReviewItem -> onClickReviewItem(event.person)
        }
    }


    private fun onClickReviewItem(person: UserUi) {
        val lastData = screenState.value.content
        this.positionOld = lastData.content.listUserUi.indexOf(person)
        if (lastData.content.showModalRegister) {
            setShowDataModal(false, person)
        } else {
            setShowDataModal(true, person)
        }
    }

    private fun setShowDataModal(status: Boolean, person: UserUi) {
        this.typeScreen = type_state_update
        showContent(
            content = content.copy(
                showModalRegister = status,
                nameDialog = person.name,
                typeUserDialog = "tpasd"
            )
        )
    }

    private fun onSaveUserDialog() {
        val lastData = screenState.value.content.content
        val personNew = UserUi(
            name = lastData.nameDialog,
            image = "",
            statusCliente = true,
            statusEmpleado = false
        )
        if (typeScreen == type_state_update) {
            if (positionOld != -1) {
                lastData.listUserUi[positionOld] = personNew
            }
        } else if (typeScreen == type_state_add) {
            lastData.listUserUi.add(personNew)
        }
        showContent(
            content = content.copy(
                showModalRegister = false,
                listUserUi = lastData.listUserUi
            )
        )
    }

    private fun hideBottomDialog() {
        showContent(
            content = content.copy(
                showModalRegister = false
            )
        )
    }

    private fun onDialogTypeName(type: String) {
        showContent(
            content = content.copy(
                typeUserDialog = type
            )
        )
    }

    private fun onDialogUserName(name: String) {
        showContent(
            content = content.copy(
                nameDialog = name
            )
        )
    }

    private fun onAddNewUser() {
        this.typeScreen = type_state_add
        showContent(
            content = content.copy(
                showModalRegister = true,
                nameDialog = "",
                typeUserDialog = ""
            )
        )


    }

    private fun getListUser() {
        executeTask(
            onSuccess = ::onUserSuccess,
            onFailure = ::onUserFailure,
        ) {
            getListUserUseCase.invoke()
        }
    }

    private fun onUserSuccess(list: List<UserUi>) {
        hidePageLoading()
        showContent(
            content = HomeScreenState(
                listUserUi = list.toMutableList()
            )
        )
    }

    private fun onUserFailure(failure: Failure) {
        hidePageLoading()
        showFailure(
            failure = failure,
            retryFunction = {}
        )
    }


}