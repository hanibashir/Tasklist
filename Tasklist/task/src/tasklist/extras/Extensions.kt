package tasklist.extras

import kotlinx.datetime.toLocalDate
import tasklist.data.UserInput.getTaskDate
import tasklist.data.UserInput.getTaskPriorityAndColor
import tasklist.data.UserInput.getTaskTime
import tasklist.data.UserInput.getTasksTextList
import tasklist.data.model.Task

fun MutableList<Task>.update(taskNumber: String) {
    //val toRemoveTasks = mutableListOf<Task>()

    val oldTasksList = filter { it.tasksInfo["number"]?.toInt()!! < taskNumber.toInt() }
    var toAddTasks = mutableListOf<Task>()

    if (isNotEmpty()) {
        toAddTasks = filter {
            it.tasksInfo["number"]?.toInt()!! > taskNumber.toInt()
        }.map {
            it.tasksInfo["number"] = (it.tasksInfo["number"]?.toInt()?.minus(1)).toString()
            it
        }.toMutableList()
    }

    if (toAddTasks.isNotEmpty()) {
        this.clear()
        this.addAll(oldTasksList)
        this.addAll(toAddTasks)
    }
}

fun MutableList<Task>.editTask(taskNumber: String, field: EditField) {

    val targetedTask: Task = this.getTargetedTask(taskNumber)

    when (field) {

        EditField.PRIORITY -> targetedTask.tasksInfo["priorityColor"] = getTaskPriorityAndColor()

        EditField.DATE -> {
            val taskNewDate = getTaskDate()
            targetedTask.tasksInfo["date"] = taskNewDate
            targetedTask.tasksInfo["dueTag"] = DueTag.T.getDueTag(taskNewDate.toLocalDate())
        }

        EditField.TIME -> targetedTask.tasksInfo["time"] = getTaskTime()
        EditField.TASK -> {
            val newTasksTextList = getTasksTextList()
            if (newTasksTextList.isNotEmpty())
                targetedTask.tasksText = newTasksTextList
        }
    }
}

fun MutableList<Task>.getTargetedTask(taskNumber: String): Task {
    return run {
        var temp: Task? = null
        for (task in this) {
            if (task.tasksInfo["number"] == taskNumber) {
                temp = task
                break
            }
        }
        temp!!
    }
}

fun String.addSpace(n: Int): String {
    var tempString = this
    for (i in 1..n) {
        tempString += " "
    }
    return tempString
}