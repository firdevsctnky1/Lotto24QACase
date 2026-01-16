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

    /**
     * Verifies that lottery title and formatted draw dates
     * are displayed correctly for 6aus49
     */
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


    /**
     * Verifies that 6aus49 renders
     * 6 regular numbers and 1 super number
     */
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

    /**
     * Verifies that the exact lottery numbers
     * are rendered for 6aus49 (value-level assertion)
     */
    @Test
    fun shouldRenderExpectedLotteryNumbers_for6aus49() {
        // Arrange
        val lotto = TestData.lotto6aus49()
        val expectedNumbers = listOf("18", "21", "23", "25", "26", "48")
        val expectedSuperNumber = "1"

        // Act
        composeRule.setContent {
            LottoResultItem(lotto = lotto)
        }

        // Assert
        expectedNumbers.forEach { number ->
            composeRule.onNodeWithText(number).assertIsDisplayed()
        }
        composeRule.onNodeWithText(expectedSuperNumber).assertIsDisplayed()
    }

    /**
    * Verifies that the root Lotto card
    * is present in the UI
    */
    @Test
    fun shouldHaveRootItemTag() {
        // Arrange
        val lotto = TestData.lotto6aus49()

        // Act
        composeRule.setContent { LottoResultItem(lotto = lotto) }

        // Assert
        composeRule.onNodeWithTag(LottoTags.ITEM).assertIsDisplayed()
    }

    /**
     * Verifies EuroJackpot behavior:
     * - 5 normal numbers + 2 extra numbers
     * - No SUPER_NUMBER circles
     */
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

    /**
     * Verifies that the numbers row
     * is visible for 6aus49
     */
    @Test
    fun shouldShowNumbersRow() {
        // Arrange
        val lotto = TestData.lotto6aus49()

        // Act
        composeRule.setContent { LottoResultItem(lotto = lotto) }

        // Assert
        composeRule.onNodeWithTag(LottoTags.NUMBERS_ROW).assertIsDisplayed()
    }

/**
 * Verifies that the UI does not render a super number
 * when superNumber list is empty
 */
@Test
fun shouldNotRenderSuperNumber_whenSuperNumberIsEmpty() {
    val lotto = TestData.lottoWithoutSuperNumber()

    composeRule.setContent { LottoResultItem(lotto = lotto) }

    composeRule.onAllNodesWithTag(LottoTags.NUMBER).assertCountEquals(6)
    composeRule.onNodeWithTag(LottoTags.SUPER_NUMBER).assertDoesNotExist()

   }
}


