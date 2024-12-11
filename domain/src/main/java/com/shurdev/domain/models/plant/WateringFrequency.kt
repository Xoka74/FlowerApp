package com.shurdev.domain.models.plant

enum class WateringFrequency {
    OnceAWeek,
    TwiceAWeek,
    OnceEveryTwoWeeks,
    OnceAMonth;

    fun toMillis(): Long {
        return when (this) {
            OnceAWeek -> MILLIS_IN_WEEK
            TwiceAWeek -> MILLIS_IN_WEEK / 2
            OnceEveryTwoWeeks -> MILLIS_IN_WEEK * 2
            OnceAMonth -> MILLIS_IN_WEEK * 4
        }
    }

    companion object {
        const val MILLIS_IN_WEEK: Long = 7 * 24 * 60 * 60 * 1000
    }
}