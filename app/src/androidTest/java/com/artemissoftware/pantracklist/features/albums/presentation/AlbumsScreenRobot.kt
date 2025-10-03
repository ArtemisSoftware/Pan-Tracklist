package com.artemissoftware.pantracklist.features.albums.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.artemissoftware.pantracklist.core.presentation.util.test.CoreTestTags
import com.artemissoftware.pantracklist.features.albums.presentation.util.TestTags

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
/*
    fun assertEmptyCartListIsDisplayed(): CartScreenRobot<T> {
        val cartList = composeRule
            .onNodeWithTag(TestTags.CART_LIST)

        cartList
            .assertIsDisplayed()

        cartList
            .onChildAt(0)
            .assertIsNotDisplayed()

        return this.assertCartTotalIsDisplayed(0.0)
    }

    fun assertCartTotalIsDisplayed(total: Double): CartScreenRobot<T> {
        composeRule
            .onNodeWithTag(TestTags.CART_TOTAL_BAR)
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag(TestTags.TOTAL_VALUE)
            .assertIsDisplayed()
            .assertTextEquals(total.toString())
        return this
    }

    fun navigateToSearchProduct(): CartScreenRobot<T> {
        composeRule
            .onNodeWithTag(TestTags.CART_ADD_BUTTON)
            .assertIsDisplayed()
            .performClick()
        return this
    }

    fun navigateToProductDetail(): CartScreenRobot<T> {
        val cartList = composeRule
            .onNodeWithTag(TestTags.CART_LIST)

        cartList
            .onChildAt(0)
            .assertIsDisplayed()
            .performClick()

        return this
    }

    fun assertProductIsDisplayed(position: Int = 0, comment: String): CartScreenRobot<T> {
        val cartList = composeRule
            .onNodeWithTag(TestTags.CART_LIST)

        cartList
            .assertIsDisplayed()

        cartList
            .onChildAt(position)
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_CARD_COMMENT, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals(comment)
        return this
    }
    */
}