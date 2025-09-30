package com.artemissoftware.pantracklist.core.domain
import com.artemissoftware.pantracklist.core.domain.error.DataError
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

internal object HandleRequest {

    inline fun <T> safeRequest(apiCall: () -> T): Resource<T> {
        return try {
            Resource.Success(data = apiCall())
        } catch (ex: Exception) {
            Resource.Failure(error = handleException(ex))
        }
    }

    fun handleException(ex: Exception): DataError {
        return when (ex) {
            is UnknownHostException -> {
                DataError.Remote.UNKNOWN
            }

            is ConnectException -> {
                DataError.Remote.SERVER
            }

            is SocketTimeoutException -> {
                DataError.Remote.REQUEST_TIMEOUT
            }
            is CancellationException -> {
                throw ex
            }
            else -> DataError.Remote.UNKNOWN
        }
    }
}