package features.tasks.presentation.mapper

import core.utils.State
import features.tasks.domain.entities.Task
import features.tasks.presentation.reducers.state.TasksState
import features.tasks.presentation.reducers.state.TasksStateItem
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlin.jvm.JvmName

fun Task.mapToTaskStateItem() = TasksStateItem(
    id = id,
    title = title,
    description = description,
    priority = priority.name,
    status = status.name,
)

fun List<Task>.mapToTaskStateItemList() = map { it.mapToTaskStateItem() }.toPersistentList()

@JvmName("mapListTaskToTaskState")
fun State<List<Task>>.mapToTasksState(
    state: TasksState
): TasksState {
    return when (this) {
        is State.Error -> state.copy(
            isLoading = false,
            error = message,
        )
        State.Loading -> state.copy(
            isLoading = true,
        )
        is State.Success -> state.copy(
            isLoading = false,
            tasks = data.mapToTaskStateItemList()
        )
    }
}

@JvmName("mapUnitToTaskState")
fun State<Unit>.mapToTasksState(
    state: TasksState
): TasksState {
    return when (this) {
        is State.Error -> state.copy(
            isLoading = false,
            error = message,
        )
        State.Loading -> state.copy(
            isLoading = true,
        )
        is State.Success -> state.copy(
            isLoading = false,
        )
    }
}