package com.lotto24.qacase

import com.lotto24.qacase.domain.model.LottoDomain

object TestData {

    // Returns a 6aus49 lotto result used in UI tests
    fun lotto6aus49(): LottoDomain = LottoDomain(
        lottery = "6aus49",
        lastDrawDate = "2026-01-14",
        nextDrawDate = "2026-01-17",
        numbers = listOf(18, 21, 23, 25, 26, 48),
        superNumber = listOf(1),
        isEuroJackpot = false
    )

    // Returns a EuroJackpot lotto result used in UI tests
    fun euroJackpot(): LottoDomain = LottoDomain(
        lottery = "EuroJackpot",
        lastDrawDate = "2026-01-13",
        nextDrawDate = "2026-01-16",
        numbers = listOf(2, 16, 27, 33, 47),
        superNumber = listOf(6, 12),
        isEuroJackpot = true
    )

     //Lotto result without super number (edge case)
    fun lottoWithoutSuperNumber(): LottoDomain = LottoDomain(
        lottery = "6aus49",
        lastDrawDate = "2026-01-14",
        nextDrawDate = "2026-01-17",
        numbers = listOf(5, 12, 19, 28, 34, 45),
        superNumber = emptyList(),
        isEuroJackpot = false
    )

}
