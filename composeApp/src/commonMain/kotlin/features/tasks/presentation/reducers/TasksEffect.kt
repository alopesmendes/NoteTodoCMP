package features.tasks.presentation.reducers

import androidx.compose.runtime.Immutable
import core.utils.Reducer

@Immutable
sealed interface TasksEffect : Reducer.ViewEffect {
    data class NavigateToTaskDetails(val taskId: Long): TasksEffect
}