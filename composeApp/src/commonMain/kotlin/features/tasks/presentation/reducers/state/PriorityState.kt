package features.tasks.presentation.reducers.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DensityMedium
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import core.presentation.state.DropMenuItemState
import kotlinx.collections.immutable.persistentListOf
import notetodo.composeapp.generated.resources.Res
import notetodo.composeapp.generated.resources.priority_high
import notetodo.composeapp.generated.resources.priority_highest
import notetodo.composeapp.generated.resources.priority_low
import notetodo.composeapp.generated.resources.priority_lowest
import notetodo.composeapp.generated.resources.priority_medium
import org.jetbrains.compose.resources.StringResource

@Immutable
sealed interface PriorityState : DropMenuItemState {
    data object Lowest : PriorityState {
        override val textId: StringResource
            get() = Res.string.priority_lowest
        override val icon: ImageVector
            get() = Icons.Filled.KeyboardDoubleArrowDown
        override val color: Color
            get() = Color.Blue
    }

    data object Low : PriorityState {
        override val textId: StringResource
            get() = Res.string.priority_low
        override val icon: ImageVector
            get() = Icons.Filled.KeyboardArrowDown
        override val color: Color
            get() = Color.Cyan
    }

    data object Medium : PriorityState {
        override val textId: StringResource
            get() = Res.string.priority_medium
        override val icon: ImageVector
            get() = Icons.Filled.DensityMedium
        override val color: Color
            get() = Color.Green
    }

    data object High : PriorityState {
        override val textId: StringResource
            get() = Res.string.priority_high
        override val icon: ImageVector
            get() = Icons.Filled.KeyboardArrowUp
        override val color: Color
            get() = Color.Magenta
    }

    data object Highest : PriorityState {
        override val textId: StringResource
            get() = Res.string.priority_highest
        override val icon: ImageVector
            get() = Icons.Filled.KeyboardDoubleArrowUp
        override val color: Color
            get() = Color.Red
    }

    companion object {
        val values = persistentListOf(
            Lowest,
            Low,
            Medium,
            High,
            Highest,
        )
    }
}
