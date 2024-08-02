package features.tasks.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.components.ErrorComponent
import core.presentation.components.LoadingComponent
import features.tasks.presentation.reducers.state.TasksState

@Composable
fun TaskContent(
    modifier: Modifier = Modifier,
    state: TasksState,
    onTaskClick: (Long) -> Unit = {},
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
            TasksListView(
                modifier = Modifier.fillMaxWidth(),
                tasksStateItems = state.tasks,
                onTaskClick = {
                    onTaskClick(it.id)
                }
            )
        }
    }
}