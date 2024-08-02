package features.categories.presentation.reducers.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CategoryStateItem(
    val id: Long = 0L,
    val name: String = "",
    val color: Color = Color.Unspecified,
)
