package com.artemissoftware.pantracklist.util.mockserver.dispatcher

import com.artemissoftware.pantracklist.util.mockserver.MockServerFiles.*
import com.artemissoftware.pantracklist.util.mockserver.MockServerFiles.Companion.toMockResponse
import com.artemissoftware.pantracklist.util.mockserver.MockServerFiles.Companion.toMockServerFile
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher {
    fun successDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.toMockServerFile()) {
                    ALBUMS_RESPONSE ->  { ALBUMS_RESPONSE.toMockResponse() }
                    NO_RESPONSE -> { MockResponse().setResponseCode(200).setBody("") }
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