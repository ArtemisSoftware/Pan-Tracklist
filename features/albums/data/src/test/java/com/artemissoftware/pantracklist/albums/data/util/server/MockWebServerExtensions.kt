package com.artemissoftware.pantracklist.albums.data.util.server

import com.artemissoftware.pantracklist.albums.data.util.JsonUtil
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueResponse(filePath: String, code: Int = 200) {

    val source = JsonUtil.getJsonContent(filePath)
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(source)
    )
}