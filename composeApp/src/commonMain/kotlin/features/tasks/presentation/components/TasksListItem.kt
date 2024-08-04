package features.tasks.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import features.tasks.presentation.reducers.state.PriorityState
import features.tasks.presentation.reducers.state.StatusState
import features.tasks.presentation.reducers.state.TasksStateItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TasksListItem(
    modifier: Modifier = Modifier,
    task: TasksStateItem,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
   Card(
        modifier = modifier,
        elevation = 4.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#${task.id}",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )

            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = task.description ?: "",
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
            }

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
            ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colors.error,
                )
            }
        }
    }

}

@Preview
@Composable
fun TasksListItemPreview() {
    TasksListItem(
        task = TasksStateItem(
            id = 1L,
            title = "Title",
            description = "Description",
            priority = PriorityState.Lowest,
            status = StatusState.Todo,
        ),
        onClick = { },
        onDeleteClick = { }
    )
}