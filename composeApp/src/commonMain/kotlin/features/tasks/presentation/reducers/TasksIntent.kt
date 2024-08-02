package features.tasks.presentation.reducers

import androidx.compose.runtime.Immutable
import core.utils.Reducer

@Immutable
sealed interface TasksIntent : Reducer.ViewIntent {
    data object FetchTasks : TasksIntent

    data class DeleteTask(val id: Long) : TasksIntent
}