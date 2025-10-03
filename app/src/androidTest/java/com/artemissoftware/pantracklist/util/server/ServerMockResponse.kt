package com.artemissoftware.pantracklist.util.server

import androidx.test.platform.app.InstrumentationRegistry
import com.artemissoftware.pantracklist.util.JsonUtil
import com.artemissoftware.pantracklist.util.server.MockData.ALBUMS_RESPONSE_FILE
import okhttp3.mockwebserver.MockResponse

private object MockData {

    const val ALBUMS_RESPONSE_FILE = "albums.json"
}

enum class ServerMockResponse(val path: String, val file: String){
    ALBUMS_RESPONSE(path = "/img/shared/technical-test.json", file = ALBUMS_RESPONSE_FILE),
}

fun ServerMockResponse.toMockResponse(): MockResponse{
    return MockResponse()
        .setResponseCode(200)
        .setBody(readJsonFromAsset(file))
}

fun readJsonFromAsset(fileName: String): String {
    val context = InstrumentationRegistry.getInstrumentation().context
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}