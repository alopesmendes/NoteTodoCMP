package features.tasks.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import features.tasks.presentation.reducers.state.TasksStateItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TasksListItem(
    modifier: Modifier = Modifier,
    task: TasksStateItem,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = 4.dp,
        onClick = onClick
    ) {
        Row(
            modifier = modifier,
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
            priority = "LOWEST",
            status = "IN_PROGRESS"
        ),
        onClick = { },
    )
}