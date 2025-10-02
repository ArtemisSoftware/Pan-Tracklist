package com.artemissoftware.pantracklist.util.server

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

fun MockWebServer.enqueueResponse(filePath: String, code: Int = 200) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)

    val source = inputStream?.let { inputStream.source().buffer() }
        ?: throw Exception("The file <$filePath> does not exist")

    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(source.readString(StandardCharsets.UTF_8))
    )
}