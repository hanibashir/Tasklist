package tasklist.data

import tasklist.data.model.Task
import tasklist.extras.Messages.ACTION_NOT_VALID
import tasklist.extras.Messages.CHOOSE_ACTION
import tasklist.extras.Messages.CHOOSE_PRIORITY
import tasklist.extras.Messages.DATE_NOT_VALID
import tasklist.extras.Messages.INPUT_DATE
import tasklist.extras.Messages.INPUT_TIME
import tasklist.extras.Messages.TIME_NOT_VALID
import tasklist.extras.*
import tasklist.extras.Messages.INPUT_TASK_NUMBER
import tasklist.extras.Messages.INVALID_FIELD
import tasklist.extras.Messages.INVALID_TASK_NUMBER
import tasklist.extras.Messages.ADD_NEW_TASK
import tasklist.service.TaskService

object UserInput {
    fun getUserInput(): Boolean {
        do {
            println(CHOOSE_ACTION)
            val action = readln()
            executeAction(action)
            if (action.isBlank()) println(ACTION_NOT_VALID)
        } while (action != Actions.END.value)
        return false
    }

    private fun executeAction(action: String) {
        while (action.isNotBlank()) {
            when (action) {
                Actions.ADD.value -> {
                    TaskService.addNewTask()
                    break
                }

                Actions.PRINT.value -> {
                    TaskService.printTasks()
                    break
                }

                Actions.DELETE.value -> {
                    TaskService.deleteTask()
                    break
                }

                Actions.Edit.value -> {
                    TaskService.editTask()
                    break
                }

                Actions.END.value -> {
                    TaskService.saveTasks()
                    break
                }

                else -> {
                    println(ACTION_NOT_VALID)
                    break
                }
            }
        }
    }

    // get the list Tasks Text
    fun getTasksTextList(): List<String> {
        println(ADD_NEW_TASK)
        var taskText = readln().trim()
        val tasksTextList = mutableListOf<String>()

        while (taskText.isNotBlank()) {
            tasksTextList.add(taskText)
            taskText = readln().trim()
        }

        return tasksTextList
    }

    fun getTaskPriorityAndColor(): String {
        var priority: String
        do {
            println(CHOOSE_PRIORITY)
            priority = readln().uppercase()
        } while (!Priority.values().any { it.name == priority })

        val priorityColor = when (Priority.valueOf(priority).name) {
            Priority.C.name -> ConsoleColors.RED
            Priority.H.name -> ConsoleColors.YELLOW
            Priority.N.name -> ConsoleColors.GREEN
            Priority.L.name -> ConsoleColors.BLUE
            else -> "Wrong priority color"
        }

        return priorityColor
    }

    fun getTaskDate(): String {
        var dateString: String
        do {
            println(INPUT_DATE)
            dateString = readln()
            if (!isValidDate(dateString)) println(DATE_NOT_VALID)
        } while (!isValidDate(dateString))

        return monthDayFormat(dateString)
    }

    fun getTaskTime(): String {
        var timeString: String
        do {
            println(INPUT_TIME)
            timeString = readln()
            if (!isValidTime(timeString)) println(TIME_NOT_VALID)
        } while (!isValidTime(timeString))

        return timeFormat(timeString)
    }

    fun getTaskField(): EditField {
        var field: String
        do {
            println(Messages.INPUT_FIELD_TO_EDIT)
            field = readln().uppercase()
            if (!EditField.values().any { it.name == field }) println(INVALID_FIELD)

        } while (!EditField.values().any { it.name == field })

        return EditField.valueOf(field)
    }

    fun getTaskNumber(tasksList: MutableList<Task>): String {
        var taskNumber = 0
        var taskFound = false
        do {
            println(INPUT_TASK_NUMBER + "(1-${tasksList.count()}):")
            taskNumber = try {
                readln().toInt()
            } catch (nfe: NumberFormatException) {
                println(INVALID_TASK_NUMBER)
                continue
            }

            for (task in tasksList) {
                if (task.tasksInfo["number"] == taskNumber.toString()) {
                    taskFound = true
                    break
                }
            }

            if (!taskFound) println(INVALID_TASK_NUMBER)

        } while (!taskFound)

        return taskNumber.toString()
    }
}
