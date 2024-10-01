package com.lerp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lerp.domain.Failure
import com.lerp.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


fun <T> ViewModel.executeTask(
    onSuccess: (T) -> Unit,
    onFailure: (Failure) -> Unit,
    task: suspend () -> Result<T>,
) {
    viewModelScope.launch {
        try {
            val result = task.invoke()
            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> onSuccess.invoke(result.data)
                    is Result.Error -> onFailure.invoke(result.error)
                }
            }
        } catch (e: Throwable) {
            withContext(Dispatchers.Main) {
                onFailure.invoke(Failure.Others)
            }
        }
    }
}
