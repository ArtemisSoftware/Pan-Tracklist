package com.artemissoftware.pantracklist.core.network

import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.core.domain.error.DataError
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

internal object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): Resource<T> {
        return try {
            val result = callFunction.invoke()
            Resource.Success(data = result)
        } catch (ex: Exception) {
            if (ex is CancellationException) throw ex
            Resource.Failure(error = handleException(ex))
        }
    }

    private fun handleException(ex: Exception): DataError {
        return when (ex) {
            is UnknownHostException -> DataError.Remote.UNKNOWN
            is ConnectException -> DataError.Remote.SERVER
            is SocketTimeoutException -> DataError.Remote.REQUEST_TIMEOUT
            is JsonDataException -> DataError.Remote.SERIALIZATION
            is HttpException -> mapHttpException(ex)
            else -> DataError.Remote.UNKNOWN
        }
    }

    private fun mapHttpException(ex: HttpException): DataError {
        return when (ex.code()) {
            in 500..599 -> DataError.Remote.SERVER
            408 -> DataError.Remote.REQUEST_TIMEOUT
            429 -> DataError.Remote.TOO_MANY_REQUESTS
            else -> DataError.Remote.UNKNOWN
        }
    }
}