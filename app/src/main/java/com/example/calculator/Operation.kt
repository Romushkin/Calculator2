package com.example.calculator

class Operation {

    fun timeToSeconds(timeStr: String): Int {
        val regex = Regex("(\\d+)([hms])")
        var totalSeconds = 0

        for (match in regex.findAll(timeStr)) {
            val value = match.groups[1]?.value?.toInt() ?: 0
            val unit = match.groups[2]?.value

            totalSeconds += when (unit) {
                "h" -> value * 3600
                "m" -> value * 60
                "s" -> value
                else -> 0
            }
        }

        return totalSeconds
    }

    fun secondsToTime(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60

        if (hours != 0) {
            return "${hours}h${minutes}m${secs}s"
        } else if (minutes != 0) {
            return "${minutes}m${secs}s"
        } else return "${secs}s"
    }

    fun sum(time1: String, time2: String): String {
        val totalSeconds = timeToSeconds(time1) + timeToSeconds(time2)
        return secondsToTime(totalSeconds)
    }

    fun dif(time1: String, time2: String): String {
        val totalSeconds = timeToSeconds(time1) - timeToSeconds(time2)
        return secondsToTime(totalSeconds)
    }
}