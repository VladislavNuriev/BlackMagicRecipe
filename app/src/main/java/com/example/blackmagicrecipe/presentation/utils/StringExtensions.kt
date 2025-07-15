package com.example.blackmagicrecipe.presentation.utils


fun String.convertBrewingTimeToInt(): Int {
    val secondsInMinute = 60
    return try {
        val strList = split(":")
        require(strList.size == 2) { "Invalid time format. Expected 'mm:ss'" }
        val minutes = strList[0].toInt().coerceAtLeast(0)
        val seconds = strList[1].toInt().let {
            require(it in 0..59) { "Seconds must be between 0 and 59" }
            it
        }
        minutes * secondsInMinute + seconds
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("Time components must be numeric values", e)
    }
}