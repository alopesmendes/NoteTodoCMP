package features.categories.presentation.mapper

import androidx.compose.ui.graphics.Color
import core.utils.State
import core.utils.Tools
import features.categories.domain.entities.Category
import features.categories.presentation.reducers.state.CategoryState
import features.categories.presentation.reducers.state.CategoryStateItem
import kotlinx.collections.immutable.persistentListOf

fun Category.mapToCategoryStateItem(): CategoryStateItem = CategoryStateItem(
    id = id,
    name = name,
    color = Color(Tools.parseToULong(color)),
)

fun List<Category>.mapToCategoryStateItems(): List<CategoryStateItem> = map { it.mapToCategoryStateItem() }

fun State<List<Category>>.mapToCategoryState(
    state: CategoryState
): CategoryState = when (this) {
    is State.Error -> state.copy(
        isLoading = false,
        error = message,
    )
    State.Loading -> state.copy(isLoading = true)
    is State.Success -> CategoryState(
        isLoading = false,
        categories = persistentListOf(
            *state.categories.toTypedArray(),
            *data.mapToCategoryStateItems().toTypedArray(),
        )
    )
}