package features.tasks.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import core.presentation.components.MainDialog
import features.tasks.presentation.reducers.state.TasksStateItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TaskDialogView(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    onVisibilityChange: (Boolean) -> Unit = {},
    onSave: (TasksStateItem) -> Unit = {}
) {
    var taskStateItem by remember {
        mutableStateOf(TasksStateItem())
    }
    MainDialog(
        modifier = modifier,
        isVisible = isVisible,
        onVisibilityChange = onVisibilityChange,
        content = {
            TaskForm(
                modifier = Modifier,
                state = taskStateItem,
                onStateChange = { taskStateItem = it },
            )
        },
        onConfirm = { onSave(taskStateItem) }
    )
}

@Preview
@Composable
fun TaskDialogViewPreview() {
    TaskDialogView(
        isVisible = true,
        onVisibilityChange = {}
    )
}