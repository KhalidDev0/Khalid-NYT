package com.khalid.network.utils

import com.khalid.analytics.ErrorReporter
import com.khalid.common.utils.AppResult
import com.khalid.common.utils.ErrorMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafeFlowHandler @Inject constructor(
    private val errorReporter: ErrorReporter
) {
    operator fun <ConvertType> invoke(
        flowBlock: suspend () -> AppResult<ConvertType>
    ) = flow {
        emit(flowBlock())
    }.catch {
        errorReporter.log(it)
        emit(AppResult.Error(ErrorMapper.map(it)))
    }
}
