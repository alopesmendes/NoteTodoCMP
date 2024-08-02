package features.tasks.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.tasks.presentation.reducers.state.TasksStateItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TasksListView(
    modifier: Modifier = Modifier,
    tasksStateItems: ImmutableList<TasksStateItem>,
    onTaskClick: (TasksStateItem) -> Unit = {},
    onTaskDelete: (TasksStateItem) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(tasksStateItems, key = { it.id }) {
            TasksListItem(
                modifier = Modifier.fillMaxWidth(),
                task = it,
                onClick = { onTaskClick(it) },
                onDeleteClick = { onTaskDelete(it) }
            )
        }
    }
}

@Preview
@Composable
fun TasksListViewPreview() {
    TasksListView(
        tasksStateItems = (1..10).map {
            TasksStateItem(
                id = it.toLong(),
                title = "Title $it",
                description = "Description $it",
                priority = "LOWEST",
                status = "IN_PROGRESS"
            )
        }.toPersistentList()
    )
}