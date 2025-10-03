package com.artemissoftware.pantracklist.features.albums.presentation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.artemissoftware.pantracklist.core.designsystem.theme.PanTracklistTheme
import com.artemissoftware.pantracklist.feature.album.presentation.albums.navigation.AlbumsGraph
import com.artemissoftware.pantracklist.test.TestActivity
import com.artemissoftware.pantracklist.util.PanTracklistAndroidTest
import com.artemissoftware.pantracklist.util.mockserver.dispatcher.MockServerDispatcher
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AlbumsScreenTest: PanTracklistAndroidTest() {

    @get:Rule
    val composeRule = createAndroidComposeRule<TestActivity>()

    private lateinit var robot: AlbumsScreenRobot<TestActivity>

    override fun setUp() {
        super.setUp()
        robot = AlbumsScreenRobot(composeRule)
    }

    @Test
    fun load_albums() = runBlocking<Unit>{

        mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        composeRule.setContent {
            PanTracklistTheme {
                AlbumsGraph()
            }
        }

        composeRule.awaitIdle()

        robot
            .assertAlbumListIsDisplayed()
    }

    @Test
    fun load_albums_with_error() = runBlocking<Unit>{

        mockServer.dispatcher = MockServerDispatcher().errorDispatcher()

        composeRule.setContent {
            PanTracklistTheme {
                AlbumsGraph()
            }
        }

        composeRule.awaitIdle()

        robot
            .assertErrorPlaceHolderDisplayed()
    }

    @Test
    fun load_albums_with_error_reload_data_get_albums() = runBlocking<Unit>{

        mockServer.dispatcher = MockServerDispatcher().errorDispatcher()

        composeRule.setContent {
            PanTracklistTheme {
                AlbumsGraph()
            }
        }

        composeRule.awaitIdle()

        robot
            .assertErrorPlaceHolderDisplayed()


        mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        robot
            .performErrorPlaceHolderClick()

        composeRule.awaitIdle()

        robot
            .assertAlbumListIsDisplayed()

    }
}