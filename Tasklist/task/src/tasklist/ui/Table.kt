package tasklist.ui

import tasklist.data.model.Task
import tasklist.extras.addSpace

private const val rowFormat = "| %s  | %4s | %1s | %s | %s |%-44s|%n"

object Table {
    fun create(tasksList: MutableList<Task>): String {

        print(
            String.format("+----+------------+-------+---+---+--------------------------------------------+%n")
                    + String.format("| N  |    Date    | Time  | P | D |                   Task                     |%n")
                    + String.format("+----+------------+-------+---+---+--------------------------------------------+%n")
        )

        val tableData: MutableList<List<String>> = mutableListOf()

        tasksList.forEach {


                val taskInfo = it.tasksInfo
                val tasksTextList = it.tasksText
                tableData.add(
                    listOf(
                        taskInfo["number"].toString(),
                        taskInfo["date"].toString(),
                        taskInfo["time"].toString(),
                        taskInfo["priorityColor"].toString(),
                        taskInfo["dueTag"].toString(),
                        tasksTextList.joinToString("\n") //{ it.description }
                    )
                )
        }

        return tableFormat(tableData)
    }

    private fun tableFormat(tableData: MutableList<List<String>>): String {
        val sb = StringBuilder("")
        val tasksListText = mutableListOf<String>()


        tableData.forEach { rowData ->

            tasksListText.addAll(rowData[5].split("\n"))

            if (rowData.count() == 1) {
                sb.append(
                    String.format(
                        rowFormat,
                        rowData[0],
                        rowData[1],
                        rowData[2],
                        rowData[3],
                        rowData[4],
                        tasksListText[0],
                    )
                )

            } else {
                sb.append(formatTaskText(rowData, tasksListText))
            }
            sb.append(
                String.format("+----+------------+-------+---+---+--------------------------------------------+%n")
            )
            tasksListText.clear()
        }

        return sb.toString()
    }

    private fun formatTaskText(rowData: List<String>, tasksListText: MutableList<String>): String {
        val sb = StringBuilder("")
        val splitRegex = Regex(""".{0,44}""")
        val splitText = mutableListOf<String>()

        tasksListText.forEach { taskText ->
            splitRegex.findAll(taskText).forEach {
                if (it.value != "")
                    splitText.add(it.value)
            }
        }

        for (descIndex in 0..splitText.lastIndex) {

            if (descIndex == 0) {
                sb.append(
                    String.format(
                        rowFormat,
                        rowData[0],
                        rowData[1],
                        rowData[2],
                        rowData[3],
                        rowData[4],
                        splitText.first(),
                    )
                )
            } else {
                sb.append(
                    String.format(
                        rowFormat,
                        "".addSpace(1),
                        "".addSpace(10),
                        "".addSpace(5),
                        "".addSpace(1),
                        "".addSpace(1),
                        splitText[descIndex]
                    )
                )
            }
        }
        return sb.toString()
    }
}

