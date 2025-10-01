package com.artemissoftware.pantracklist.presentation.composables
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.artemissoftware.pantracklist.core.designsystem.theme.PanTracklistTheme
import com.artemissoftware.pantracklist.presentation.albums.composables.AlbumCard
import com.artemissoftware.pantracklist.presentation.util.TestTags.ALBUM_CARD_CONTENT
import com.artemissoftware.pantracklist.presentation.util.TestTags.ALBUM_CARD_IMAGE
import com.artemissoftware.pantracklist.presentation.util.TestTags.ALBUM_CARD_NAME
import com.artemissoftware.pantracklist.util.TestInstrumentedData
import org.junit.Rule
import org.junit.Test

class AlbumCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Assert all nodes on card are available`() {

        val album = TestInstrumentedData.albumList.first()

        composeTestRule.setContent {
            PanTracklistTheme {
                AlbumCard(
                    album = album,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithTag(ALBUM_CARD_CONTENT, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(ALBUM_CARD_IMAGE, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(ALBUM_CARD_NAME, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals(album.title)
    }
}