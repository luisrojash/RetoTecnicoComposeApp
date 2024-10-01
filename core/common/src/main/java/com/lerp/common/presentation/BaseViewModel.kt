package com.lerp.common.presentation

import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lerp.common.R
import com.lerp.domain.Failure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel<T, Event>(protected val resources: Resources
) : ViewModel() {

    private val initialContent by lazy { initialContent() }
    abstract fun initialContent(): T

    private val initialLoadingPage by lazy { initialLoadingPage() }
    abstract fun initialLoadingPage(): Boolean

    private val _content = MutableStateFlow<T>(initialContent)
    protected var content: T by ObservableState(_content)

    private val _dialog = MutableStateFlow<DialogState?>(null)
    private var dialog: DialogState? by ObservableState(_dialog)

    private val _loadingPage = MutableStateFlow<Boolean>(initialLoadingPage)
    private var loadingPage by ObservableState(_loadingPage)

    abstract fun initialLoadingContent(): Boolean

    private val initialLoadingContent by lazy { initialLoadingContent() }
    private val _isLoadingContent = MutableStateFlow<Boolean>(initialLoadingContent)
    private var isLoadingContent by ObservableState(_isLoadingContent)

    val screenState: StateFlow<ScreenState<T>> = combine(
        _content,
        _loadingPage,
        _dialog,
        _isLoadingContent
    ) { content, isLoadingPage, dialog, isLoadingContent ->
        ScreenState(
            content = ContentState<T>(
                content = content,
                isLoadingPage = isLoadingPage,
                isLoadingContent = isLoadingContent
            ), dialog
        )
    }.stateIn(
        viewModelScope, SharingStarted.Lazily, initialValue = ScreenState(
            content = ContentState<T>(
                content = initialContent,
                isLoadingPage = initialLoadingPage,
                isLoadingContent = initialLoadingContent
            ),
            null
        )
    )
    protected fun showContent(content: T) {
        this.content = content
    }

    protected fun showPageLoading() {
        loadingPage = true
    }

    protected fun hidePageLoading() {
        loadingPage = false
    }

    protected fun showLoadingContent() {
        isLoadingContent = true
    }

    protected fun hideLoadingContent() {
        isLoadingContent = false
    }
    protected fun showNetworkFailure(doOnTryAgain: () -> Unit) {
        showDialog(
            title = resources.getString(R.string.msg_no_access_to_internet_title),
            description = resources.getString(R.string.msg_no_access_to_internet_description),
            actionText = resources.getString(R.string.msg_try_again),
            doOnAction = doOnTryAgain
        )
    }

    protected fun showDefaultFailure(retryFunction: () -> Unit, statusRetry: Boolean) {
        showDialog(
            title = resources.getString(R.string.msg_something_is_wrong_title),
            description = resources.getString(R.string.msg_something_is_wrong_description),
            actionText = resources.getString(R.string.msg_try_again),
            doOnAction = {
                if (statusRetry) {
                    retryFunction()
                }
            }
        )
    }
    protected fun showDialog(
        @DrawableRes icon: Int = androidx.core.R.drawable.notification_icon_background,
        title: String, description: String, actionText: String, doOnAction: () -> Unit
    ) {
        dialog = DialogState(
            icon = icon,
            title = title,
            description = description,
            actionText = actionText,
            doOnAction = {
                doOnAction()
            },
            doOnDismiss = ::hideDialog
        )
    }
    protected fun hideDialog() {
        dialog = null
    }

    protected fun showFailure(
        failure: Failure,
        retryFunction: () -> Unit,
        statusRetry: Boolean = false
    ) {
        hideLoadingContent()
        when (failure) {
            is Failure.NetworkLost -> showNetworkFailure(retryFunction)
            else -> showDefaultFailure(retryFunction, statusRetry)
        }
    }

}

class ObservableState<T>(private val stateFlow: MutableStateFlow<T>) {
    operator fun getValue(thisRef: Any?, property: Any?): T {
        return stateFlow.value
    }

    operator fun setValue(thisRef: Any?, property: Any?, value: T) {
        stateFlow.value = value
    }
}