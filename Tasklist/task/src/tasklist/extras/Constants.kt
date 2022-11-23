package tasklist.extras

object Messages {
    // action
    const val CHOOSE_ACTION = "Input an action (add, print, edit, delete, end):"
    const val ACTION_NOT_VALID = "The input action is invalid"
    // priority
    const val CHOOSE_PRIORITY = "Input the task priority (C, H, N, L):"
    // date and time
    const val INPUT_DATE = "Input the date (yyyy-mm-dd):"
    const val INPUT_TIME = "Input the time (hh:mm):"
    const val DATE_NOT_VALID = "The input date is invalid"
    const val TIME_NOT_VALID = "The input time is invalid"
    // add tasks
    const val ADD_NEW_TASK = "Input a new task (enter a blank line to end):"
    const val BLANK_TASK = "The task is blank"
    // print, edit, delete no tasks
    const val NO_TASKS = "No tasks have been input"
    // delete task
    const val INPUT_TASK_NUMBER = "Input the task number "
    const val TASK_IS_DELETED = "The task is deleted"
    const val INVALID_TASK_NUMBER = "Invalid task number"
    // edit task
    const val INPUT_FIELD_TO_EDIT = "Input a field to edit (priority, date, time, task):"
    const val INVALID_FIELD = "Invalid field"
    const val TASK_CHANGED = "The task is changed"
    //terminate
    const val GOOD_BYE = "Tasklist exiting!"
}

object ConsoleColors {
    // ASCII Escape Code Colors
    const val RED = "\u001B[101m \u001B[0m"
    const val GREEN = "\u001B[102m \u001B[0m"
    const val YELLOW = "\u001B[103m \u001B[0m"
    const val BLUE = "\u001B[104m \u001B[0m"
}