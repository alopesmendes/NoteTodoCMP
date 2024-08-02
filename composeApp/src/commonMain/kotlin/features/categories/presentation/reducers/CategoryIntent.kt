package features.categories.presentation.reducers

import androidx.compose.runtime.Immutable
import core.utils.Reducer

@Immutable
sealed interface CategoryIntent: Reducer.ViewIntent {
    data object FetchCategories: CategoryIntent
}