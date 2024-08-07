package features.tasks.presentation.mapper

import core.utils.State
import features.tasks.domain.entities.Priority
import features.tasks.domain.entities.Status
import features.tasks.domain.entities.Task
import features.tasks.presentation.reducers.state.PriorityState
import features.tasks.presentation.reducers.state.StatusState
import features.tasks.presentation.reducers.state.TasksState
import features.tasks.presentation.reducers.state.TasksItemState
import kotlinx.collections.immutable.toPersistentList
import kotlin.jvm.JvmName

fun Task.mapToTaskStateItem() = TasksItemState(
    id = id,
    title = title,
    description = description,
    priority = priority.mapToPriorityState(),
    status = status.mapToStatusState(),
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

fun Priority.mapToPriorityState(): PriorityState = when (this) {
    Priority.LOWEST -> PriorityState.Lowest
    Priority.LOW -> PriorityState.Low
    Priority.MEDIUM -> PriorityState.Medium
    Priority.HIGH -> PriorityState.High
    Priority.HIGHEST -> PriorityState.Highest
}

fun Status.mapToStatusState(): StatusState = when (this) {
    Status.TODO -> StatusState.Todo
    Status.IN_PROGRESS -> StatusState.InProgress
    Status.DONE -> StatusState.Done
}

fun PriorityState.mapToPriority(): Priority = when (this) {
    PriorityState.Lowest -> Priority.LOWEST
    PriorityState.Low -> Priority.LOW
    PriorityState.Medium -> Priority.MEDIUM
    PriorityState.High -> Priority.HIGH
    PriorityState.Highest -> Priority.HIGHEST
}

fun StatusState.mapToStatus(): Status = when (this) {
    StatusState.Todo -> Status.TODO
    StatusState.InProgress -> Status.IN_PROGRESS
    StatusState.Done -> Status.DONE
}
