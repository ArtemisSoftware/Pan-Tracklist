package com.artemissoftware.pantracklist.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.artemissoftware.pantracklist.core.database.PanTracklistDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

abstract class PanTracklistAndroidTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: PanTracklistDatabase

    protected lateinit var mockServer: MockWebServer
    protected lateinit var context: Context

    @Before
    open fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        hiltRule.inject()
        mockServer = MockWebServer()
        mockServer.start(8080)
        db.clearAllTables()
    }

    @After
    open fun tearDown() {
        mockServer.shutdown()
        db.close()
    }
}