package features.tasks.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.components.ErrorComponent
import core.presentation.components.LoadingComponent
import features.tasks.presentation.reducers.state.TasksState
import features.tasks.presentation.reducers.state.TasksItemState

@Composable
fun TaskContent(
    modifier: Modifier = Modifier,
    state: TasksState,
    onVisibilityChange: (Boolean) -> Unit = {},
    onTaskClick: (Long) -> Unit = {},
    onTaskDelete: (Long) -> Unit = {},
    onSaveTask: (TasksItemState) -> Unit = {},
) {
    when {
        state.isLoading -> {
            LoadingComponent(modifier = modifier)
        }
        state.error != null -> {
            ErrorComponent(
                modifier = modifier,
                error = state.error
            )
        }
        else -> {
            if (state.isDialogVisible) {
                TaskDialogView(
                    isVisible = state.isDialogVisible,
                    onVisibilityChange = onVisibilityChange,
                    onSave = onSaveTask
                )
            }
            TasksListView(
                modifier = Modifier.fillMaxWidth(),
                tasksItemStates = state.tasks,
                onTaskClick = {
                    onTaskClick(it.id)
                },
                onTaskDelete = {
                    onTaskDelete(it.id)
                }
            )
        }
    }
}