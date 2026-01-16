package com.lotto24.qacase

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.lotto24.qacase.ui.DateFormatter
import com.lotto24.qacase.ui.LottoResultItem
import com.lotto24.qacase.ui.LottoTags
import org.junit.Rule
import org.junit.Test

class LottoScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldDisplayLotteryTitleAndFormattedDates_for6aus49() {
        // Arrange
        val lotto = TestData.lotto6aus49()
        val expectedLastDraw = "Last Draw: ${DateFormatter.format(lotto.lastDrawDate)}"
        val expectedNextDraw = "Next Draw: ${DateFormatter.format(lotto.nextDrawDate)}"

        // Act
        composeRule.setContent { LottoResultItem(lotto = lotto) }

        // Assert
        composeRule.onNodeWithTag(LottoTags.LOTTERY).assertIsDisplayed()
        composeRule.onNodeWithText(lotto.lottery.uppercase()).assertIsDisplayed()
        composeRule.onNodeWithText(expectedLastDraw).assertIsDisplayed()
        composeRule.onNodeWithText(expectedNextDraw).assertIsDisplayed()
    }


    @Test
    fun shouldRenderSixNumbersAndOneSuperNumber_for6aus49() {
        // Arrange
        val lotto = TestData.lotto6aus49()

        // Act
        composeRule.setContent { LottoResultItem(lotto = lotto) }

        // Assert
        composeRule.onAllNodesWithTag(LottoTags.NUMBER).assertCountEquals(6)
        composeRule.onAllNodesWithTag(LottoTags.SUPER_NUMBER).assertCountEquals(1)
    }

    @Test
    fun shouldHaveRootItemTag() {
        // Arrange
        val lotto = TestData.lotto6aus49()

        // Act
        composeRule.setContent { LottoResultItem(lotto = lotto) }

        // Assert
        composeRule.onNodeWithTag(LottoTags.ITEM).assertIsDisplayed()
    }

    @Test
    fun shouldRenderSevenNumbersAndNoSuperNumber_forEuroJackpot() {
        // Arrange
        val lotto = TestData.euroJackpot()

        // Act
        composeRule.setContent { LottoResultItem(lotto = lotto) }

        // Assert:
        // 5 normal numbers + 2 extra numbers should be rendered with NUMBER tag
        composeRule.onAllNodesWithTag(LottoTags.NUMBER).assertCountEquals(7)

        // EuroJackpot should NOT show SUPER_NUMBER tag
        composeRule.onAllNodesWithTag(LottoTags.SUPER_NUMBER).assertCountEquals(0)
    }

    @Test
    fun shouldShowNumbersRow() {
        // Arrange
        val lotto = TestData.lotto6aus49()

        // Act
        composeRule.setContent { LottoResultItem(lotto = lotto) }

        // Assert
        composeRule.onNodeWithTag(LottoTags.NUMBERS_ROW).assertIsDisplayed()
    }
}
