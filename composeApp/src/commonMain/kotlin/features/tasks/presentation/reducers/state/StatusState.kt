package features.tasks.presentation.reducers.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import core.presentation.state.DropMenuItemState
import kotlinx.collections.immutable.persistentListOf
import notetodo.composeapp.generated.resources.Res
import notetodo.composeapp.generated.resources.status_done
import notetodo.composeapp.generated.resources.status_in_progress
import notetodo.composeapp.generated.resources.status_todo
import org.jetbrains.compose.resources.StringResource

@Immutable
sealed interface StatusState : DropMenuItemState {
    data object Todo : StatusState {
        override val textId: StringResource
            get() = Res.string.status_todo
        override val icon: ImageVector
            get() = Icons.Filled.Pending
        override val color: Color
            get() = Color.Gray
    }

    data object InProgress : StatusState {
        override val textId: StringResource
            get() = Res.string.status_in_progress
        override val icon: ImageVector
            get() = Icons.Filled.Timelapse
        override val color: Color
            get() = Color.Green
    }

    data object Done : StatusState {
        override val textId: StringResource
            get() = Res.string.status_done
        override val icon: ImageVector
            get() = Icons.Filled.CheckCircle
        override val color: Color
            get() = Color.Blue

    }

    companion object {
        val values = persistentListOf(
            Todo,
            InProgress,
            Done,
        )
    }
}