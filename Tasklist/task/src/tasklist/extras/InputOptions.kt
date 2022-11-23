package tasklist.extras

import kotlinx.datetime.*

enum class Actions(val value: String) {
    ADD("add"),
    PRINT("print"),
    Edit("edit"),
    DELETE("delete"),
    END("end")
}

enum class Priority { C, H, N, L }

enum class DueTag {
    I, T, O;

    fun getDueTag(tasksDate: LocalDate): String {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+2")).date
        val differenceInDays = currentDate.daysUntil(tasksDate)
        return when  {
            differenceInDays == 0 -> ConsoleColors.YELLOW
            differenceInDays > 0 -> ConsoleColors.GREEN
            else -> ConsoleColors.RED
        }
    }
}

enum class EditField { PRIORITY, DATE, TIME, TASK }
