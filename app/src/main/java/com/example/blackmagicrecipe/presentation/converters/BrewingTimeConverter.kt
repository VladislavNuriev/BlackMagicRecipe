package com.example.blackmagicrecipe.presentation.converters

fun convertBrewingTimeStringToInt(timeString: String): Int {
    val strList = timeString.split(":")
    return strList[0].toInt() * SECONDS_IN_MINUTE + strList[1].toInt()
}

fun convertBrewingTimeIntToString(timeInt: Int): String {
    val minutes = timeInt / SECONDS_IN_MINUTE
    val seconds = timeInt - minutes * SECONDS_IN_MINUTE
    return "$minutes:$seconds"
}


private const val SECONDS_IN_MINUTE = 60