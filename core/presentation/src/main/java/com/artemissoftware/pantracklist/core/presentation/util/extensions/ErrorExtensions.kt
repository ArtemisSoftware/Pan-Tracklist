package com.artemissoftware.pantracklist.core.presentation.util.extensions

import com.artemissoftware.pantracklist.core.presentation.R
import com.artemissoftware.pantracklist.core.presentation.composables.text.UiText
import com.artemissoftware.pantracklist.domain.error.DataError
import com.artemissoftware.pantracklist.domain.error.Error

fun Error.toUiText(): UiText {
    return when (this) {
        is DataError.Remote -> {
            this.asUiText()
        }
        is DataError.Local -> {
            this.asUiText()
        }
        else -> UiText.StringResource(R.string.error_not_mapped)
    }
}

private fun DataError.Remote.asUiText(): UiText {
    val message = when (this) {
        DataError.Remote.REQUEST_TIMEOUT -> R.string.connection_socket_time_out
        DataError.Remote.TOO_MANY_REQUESTS -> R.string.too_many_requests
        DataError.Remote.NO_INTERNET -> R.string.no_internet_connection
        DataError.Remote.SERVER -> R.string.connection_error
        DataError.Remote.SERIALIZATION -> R.string.serialization_error
        DataError.Remote.UNKNOWN -> R.string.unknown_error
    }

    return UiText.StringResource(message)
}

private fun DataError.Local.asUiText(): UiText {
    val message = when (this) {
        DataError.Local.DISK_FULL -> R.string.disk_full
        DataError.Local.UNKNOWN -> R.string.unknown_error
    }

    return UiText.StringResource(message)
}