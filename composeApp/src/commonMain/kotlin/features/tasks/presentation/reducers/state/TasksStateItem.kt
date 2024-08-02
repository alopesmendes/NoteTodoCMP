package features.tasks.presentation.reducers.state

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.StringResource

@Immutable
data class TasksStateItem(
    val id: Long = 0L,
    val title: String = "",
    val description: String? = null,
    val priority: String = "",
    val status: String = "",
)
