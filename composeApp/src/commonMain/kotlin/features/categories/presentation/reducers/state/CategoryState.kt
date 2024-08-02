package features.categories.presentation.reducers.state

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class CategoryState(
    val isLoading: Boolean = false,
    val categories: ImmutableList<CategoryStateItem> = persistentListOf(),
    val error: String = ""

)
