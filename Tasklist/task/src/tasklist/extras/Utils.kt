package tasklist.extras

import kotlinx.datetime.*
import java.lang.Exception
import java.lang.IllegalArgumentException

fun isValidDate(date: String): Boolean {
    return try {
        val (year, month, day) = date.split("-").map { it.toInt() }
        LocalDate(year, month, day)
        true
    } catch (iae: IllegalArgumentException) {
        false
    } catch (e: Exception) {
        false
    }
}

fun isValidTime(time: String): Boolean = time.matches("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])\$".toRegex())

fun monthDayFormat(date: String): String {
    val (year, month, day) = date.split("-").map { it.toInt() }
    var formattedDate = "$year-"
    formattedDate += if (month < 10) "0$month-" else "$month-"
    formattedDate += if (day < 10) "0$day" else day.toString()
    return formattedDate
}

fun timeFormat(time: String): String {
    var formattedTime = ""
    val (hour, minute) = time.split(":").map { it.toInt() }
    formattedTime += if (hour in 0..9) "0$hour:" else "$hour:"
    formattedTime += if (minute in 0..9) "0$minute" else minute
    return formattedTime
}
fun addSpaces(n: Int): String {
    var spaces = ""
    for (i in 0 until n)
        spaces += " "
    return spaces
}