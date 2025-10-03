package com.artemissoftware.pantracklist.util.mockserver

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


enum class MockServerFiles(val path: String, val file: String){
    ALBUMS_RESPONSE(path = "/img/shared/technical-test.json", file = "albums.json"),
    NO_RESPONSE(path = "", file = "");

    companion object{
        fun MockServerFiles.toMockResponse(): MockResponse{
            return MockResponse()
                .setResponseCode(200)
                .setBody(readJsonFromAsset(file))
        }

        fun RecordedRequest.toMockServerFile(): MockServerFiles{
            return entries.find { it.path == path } ?: NO_RESPONSE
        }

        private fun readJsonFromAsset(fileName: String): String {
            val context = InstrumentationRegistry.getInstrumentation().context
            return context.assets.open(fileName).bufferedReader().use { it.readText() }
        }
    }
}

