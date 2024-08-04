package features.tasks.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.components.DynamicSelectMenu
import features.tasks.presentation.reducers.state.PriorityState
import features.tasks.presentation.reducers.state.StatusState
import features.tasks.presentation.reducers.state.TasksStateItem
import notetodo.composeapp.generated.resources.Res
import notetodo.composeapp.generated.resources.note_dialog_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    state: TasksStateItem,
    onStateChange: (TasksStateItem) -> Unit = {},
) {

    Column(
        modifier = modifier.padding(
            horizontal = 4.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(Res.string.note_dialog_title, "${state.id}"),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.h6,
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.title,
            onValueChange = {
                onStateChange(state.copy(title = it))
            },
            label = {
                Text("Title")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.description ?: "",
            onValueChange = {
                onStateChange(state.copy(description = it))
            },
            label = {
                Text("Description")
            },
            minLines = 5,
        )

        DynamicSelectMenu(
            modifier = Modifier.fillMaxWidth(),
            selectValue = state.priority,
            options = PriorityState.values,
            onValueChange = {
                onStateChange(
                    state.copy(priority = it)
                )
            },
            label = "Priority"
        )

        DynamicSelectMenu(
            modifier = Modifier.fillMaxWidth(),
            selectValue = state.status,
            options = StatusState.values,
            onValueChange = {
                onStateChange(
                    state.copy(status = it)
                )
            },
            label = "Status",
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