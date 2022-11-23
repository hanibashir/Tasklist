package tasklist.data.json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import tasklist.data.model.Task
import java.lang.reflect.Type

object MoshiAdapter {

    private val builder = MoshiBuilder.builder()

    fun <T> getAdapter(type: Class<T>): JsonAdapter<T> {
        return builder.adapter(type)
    }

    fun <T> getAdapter(rawType: Class<T>, vararg typeArguments: Type): JsonAdapter<List<Task>> {
        val type = Types.newParameterizedType(rawType, *typeArguments)
        return builder.adapter<List<Task>>(type)
    }
}
