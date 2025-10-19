package com.khalid.common.utils

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException

object ErrorMapper {
    fun map(t: Throwable): AppError = when (t) {
        is HttpException -> AppError.Http(t.code(), t.message())
        is IOException -> AppError.Network(t)
        is SerializationException -> AppError.Serialization(t)
        else -> AppError.Unknown(t)
    }
}
