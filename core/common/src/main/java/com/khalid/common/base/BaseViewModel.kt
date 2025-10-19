package com.khalid.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalid.common.utils.AppError
import com.khalid.common.utils.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

open class BaseViewModel : ViewModel() {
    protected fun <T> onUseCaseUse(
        useCase: () -> Flow<AppResult<T>>,
        onLoading: (Boolean) -> Unit = {},
        onSuccess: (T) -> Unit = {},
        onError: (AppError) -> Unit = { _ -> },
    ) {
        useCase()
            .onStart {
                onLoading(true)
            }.onEach { res ->
                when (res) {
                    is AppResult.Success -> {
                        onLoading(false)
                        onSuccess(res.data)
                    }

                    is AppResult.Error -> {
                        onLoading(false)
                        onError(res.error)
                    }
                }
            }.launchIn(viewModelScope)
    }
}