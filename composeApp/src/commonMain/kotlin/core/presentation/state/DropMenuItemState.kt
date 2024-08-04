package core.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

@Immutable
interface DropMenuItemState {
    val textId: StringResource
    val icon: ImageVector
    val color: Color
}

