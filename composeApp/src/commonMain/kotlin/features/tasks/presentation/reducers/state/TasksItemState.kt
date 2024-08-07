package features.tasks.presentation.reducers.state

import androidx.compose.runtime.Immutable

@Immutable
data class TasksItemState(
    val id: Long = 0L,
    val title: String = "",
    val description: String? = null,
    val priority: PriorityState = PriorityState.Lowest,
    val status: StatusState = StatusState.Todo,
    val isCreated: Boolean = false,
)
