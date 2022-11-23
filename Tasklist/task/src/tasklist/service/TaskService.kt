package tasklist.service

import kotlinx.datetime.toLocalDate
import tasklist.data.UserInput
import tasklist.data.UserInput.getTaskPriorityAndColor
import tasklist.data.model.Task
import tasklist.extras.*
import tasklist.data.storage.Storage
import tasklist.data.storage.Storage.readTasksFromFile
import tasklist.extras.Messages.BLANK_TASK
import tasklist.extras.Messages.NO_TASKS
import tasklist.extras.Messages.TASK_CHANGED
import tasklist.extras.Messages.TASK_IS_DELETED
import tasklist.ui.Table

// read from file if exists otherwise assign empty list to it
private val tasksList = readTasksFromFile() ?: mutableListOf<Task>()

object TaskService {

    private var tasksNumber: Int = 1 /*run {
        val tempTaskNumberList = mutableListOf<Int>()
        if (tasksList.isEmpty()) 1
        else {
            tasksList.forEach { task ->
                task.tasksInfo["number"]?.toInt()?.let { tempTaskNumberList.add(it) }
            }
            tempTaskNumberList.last() + 1
        }
    }*/

    fun addNewTask() {
        val taskPriorityColor = getTaskPriorityAndColor()
        val taskDate = UserInput.getTaskDate()
        val taskTime = UserInput.getTaskTime()
        val tasksTextList = UserInput.getTasksTextList()

        val tasksInfo = mutableMapOf(
            "number" to tasksNumber.toString(),
            "date" to taskDate,
            "time" to taskTime,
            "priorityColor" to taskPriorityColor,
            "dueTag" to DueTag.T.getDueTag(taskDate.toLocalDate())
        )

        if (tasksTextList.isEmpty()) {
            println(BLANK_TASK)
        } else {
            tasksList.add(Task(tasksInfo, tasksTextList))

            ++tasksNumber
        }
    }

    fun printTasks() {
        if (tasksList.isEmpty())
            println(NO_TASKS)
        else {
            println(Table.create(tasksList))
        }
    }

    fun editTask() {
        if (tasksList.isEmpty())
            println(NO_TASKS)
        else {
            printTasks()
            val taskNumber = UserInput.getTaskNumber(tasksList)
            val field = UserInput.getTaskField()
            tasksList.editTask(taskNumber, field)
            println(TASK_CHANGED)
        }
    }

    fun saveTasks() {
        if (tasksList.isNotEmpty()) Storage.saveTasksToFile(tasksList)
    }

    fun deleteTask() {
        if (tasksList.isEmpty())
            println(NO_TASKS)
        else {
            printTasks()
            val taskNumber = UserInput.getTaskNumber(tasksList)
            val targetedTask: Task = tasksList.getTargetedTask(taskNumber)
            tasksList.remove(targetedTask)
            println(TASK_IS_DELETED)
            tasksList.update(taskNumber)
        }
    }
}
