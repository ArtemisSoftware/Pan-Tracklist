package com.artemissoftware.pantracklist.data.network

import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.core.domain.error.DataError
import kotlinx.coroutines.ensureActive
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.coroutineContext


suspend inline fun <reified T> safeCall(
    execute: () -> T
): Resource<T> {
    val response = try {
        execute()
    } catch (e: IOException) {
        return when (e) {
            is SocketTimeoutException -> Resource.Failure(DataError.Remote.REQUEST_TIMEOUT)
            is UnknownHostException,
            is ConnectException -> Resource.Failure(DataError.Remote.NO_INTERNET)
            else -> {
                coroutineContext.ensureActive()
                Resource.Failure(DataError.Remote.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Resource.Failure(DataError.Remote.UNKNOWN)
    }

    return responseToResult(response as Response<T>)
}

fun <T> responseToResult(
    response: Response<T>
): Resource<T> {
    return when {
        response.isSuccessful -> {
            val body = response.body()
            if (body != null) {
                Resource.Success(body)
            } else {
                Resource.Failure(DataError.Remote.SERIALIZATION)
            }
        }
        response.code() == 408 -> Resource.Failure(DataError.Remote.REQUEST_TIMEOUT)
        response.code() == 429 -> Resource.Failure(DataError.Remote.TOO_MANY_REQUESTS)
        response.code() in 500..599 -> Resource.Failure(DataError.Remote.SERVER)
        else -> Resource.Failure(DataError.Remote.UNKNOWN)
    }
}