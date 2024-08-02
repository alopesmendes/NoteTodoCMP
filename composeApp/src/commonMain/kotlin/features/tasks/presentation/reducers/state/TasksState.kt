package features.tasks.presentation.reducers.state

import androidx.compose.runtime.Immutable
import core.utils.Reducer
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class TasksState(
    val isLoading: Boolean = false,
    val tasks: ImmutableList<TasksStateItem> = persistentListOf(),
    val error: String? = null,
): Reducer.ViewState
