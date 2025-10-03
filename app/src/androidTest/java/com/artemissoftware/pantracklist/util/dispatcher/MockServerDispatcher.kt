package com.artemissoftware.pantracklist.util.dispatcher

import com.artemissoftware.pantracklist.util.server.ServerMockResponse
import com.artemissoftware.pantracklist.util.server.ServerMockResponse.*
import com.artemissoftware.pantracklist.util.server.toMockResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher {
    fun successDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    ALBUMS_RESPONSE.path -> ALBUMS_RESPONSE.toMockResponse()
                    else -> MockResponse().setResponseCode(200).setBody("")
                }
            }
        }
    }

    fun errorDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(400).setBody("")
            }
        }
    }
}