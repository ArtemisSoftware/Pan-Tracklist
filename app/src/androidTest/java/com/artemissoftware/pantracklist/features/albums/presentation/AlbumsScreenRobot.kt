package com.artemissoftware.pantracklist.features.albums.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.artemissoftware.pantracklist.core.presentation.util.test.CoreTestTags
import com.artemissoftware.pantracklist.feature.album.presentation.util.TestTags

class AlbumsScreenRobot<T : ComponentActivity>(
    private val composeRule: AndroidComposeTestRule<ActivityScenarioRule<T>, T>
) {

    fun assertAlbumListIsDisplayed(): AlbumsScreenRobot<T> {
        val albumList = composeRule
            .onNodeWithTag(TestTags.ALBUM_LIST)

        albumList
            .assertIsDisplayed()

        albumList
            .onChildAt(0)
            .assertIsDisplayed()
        return this
    }

    fun assertErrorPlaceHolderDisplayed(): AlbumsScreenRobot<T> {
        val albumList = composeRule
            .onNodeWithTag(CoreTestTags.CORE_PLACEHOLDER_CONTENT)

        albumList
            .assertIsDisplayed()

        return this
    }

    fun performErrorPlaceHolderClick(): AlbumsScreenRobot<T> {
        composeRule
            .onNodeWithTag(CoreTestTags.CORE_PLACEHOLDER_BUTTON)
            .assertIsDisplayed()
            .performClick()

        return this
    }
}