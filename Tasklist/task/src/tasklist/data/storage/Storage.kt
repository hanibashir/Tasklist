package tasklist.data.storage


import tasklist.data.json.MoshiAdapter
import tasklist.data.model.Task
import java.io.File
import java.io.FileReader
import java.io.FileWriter

object Storage {
    private val jsonFile = File("tasklist.json")
    private val adapter = MoshiAdapter.getAdapter(
        List::class.java,
        Task::class.java
    )

    fun readTasksFromFile(): MutableList<Task>? {
        var jsonTasksList: MutableList<Task>? = null
        if (jsonFile.exists()) {
            val tasksJson = FileReader(jsonFile).use { fileReader ->
                fileReader.readText()
            }
            jsonTasksList = adapter.fromJson(tasksJson)?.toMutableList()
        } else {
            jsonFile.createNewFile()
        }
        return jsonTasksList
    }

    fun saveTasksToFile(tasksList: MutableList<Task>) {
        // file already created by readTasksFromFile() fun
        FileWriter(jsonFile).use { fileWriter ->
            fileWriter.write(adapter.toJson(tasksList))
        }
    }
}