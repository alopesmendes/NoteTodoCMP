package features.tasks.presentation.reducers

import androidx.compose.runtime.Immutable
import core.utils.Reducer

@Immutable
sealed interface TasksEffect : Reducer.ViewEffect {
    data class ShowDialogTaskDetail(val taskId: Long? = null): TasksEffect
}