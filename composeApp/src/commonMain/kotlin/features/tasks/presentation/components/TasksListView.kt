package features.tasks.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.tasks.presentation.reducers.state.PriorityState
import features.tasks.presentation.reducers.state.StatusState
import features.tasks.presentation.reducers.state.TasksItemState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TasksListView(
    modifier: Modifier = Modifier,
    tasksItemStates: ImmutableList<TasksItemState>,
    onTaskClick: (TasksItemState) -> Unit = {},
    onTaskDelete: (TasksItemState) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(tasksItemStates, key = { it.id }) {
            TasksListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(),
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
        tasksItemStates = (1..10).map {
            TasksItemState(
                id = it.toLong(),
                title = "Title $it",
                description = "Description $it",
                priority = PriorityState.Lowest,
                status = StatusState.Todo,
            )
        }.toPersistentList()
    )
}