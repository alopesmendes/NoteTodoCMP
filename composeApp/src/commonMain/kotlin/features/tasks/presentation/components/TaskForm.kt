package features.tasks.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.tasks.presentation.reducers.state.TasksStateItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    state: TasksStateItem,
    onStateChange: (TasksStateItem) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            value = state.title,
            onValueChange = {
                onStateChange(state.copy(title = it))
            },
            label = {
                Text("Title")
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            value = state.description ?: "",
            onValueChange = {
                onStateChange(state.copy(description = it))
            },
            label = {
                Text("Description")
            }
        )
    }
}

@Preview
@Composable
fun TaskFormPreview() {
    TaskForm(
        state = TasksStateItem(
            title = "Title",
            description = "Description"
        )
    )
}