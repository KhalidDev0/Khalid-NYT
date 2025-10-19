package com.khalid.common.utils

sealed interface AppResult<out T> {
    data class Success<T>(val data: T): AppResult<T>
    data class Error(val error: AppError): AppResult<Nothing>
}

sealed interface AppError {
    data class Http(val code: Int, val message: String? = null): AppError
    data class Network(val cause: Throwable): AppError
    data class Serialization(val cause: Throwable): AppError
    data class Unknown(val cause: Throwable): AppError
}

fun AppError.codeAndMessage(): Pair<Int?, String?> = when (this) {
    is AppError.Http -> code to message
    is AppError.Network -> null to cause.message
    is AppError.Serialization -> null to cause.message
    is AppError.Unknown -> null to cause.message
}