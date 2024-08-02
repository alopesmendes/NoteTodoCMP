package features.categories.presentation.reducers

import androidx.compose.runtime.Immutable
import core.utils.Reducer

@Immutable
sealed interface CategoryEffect: Reducer.ViewEffect {
    data class NavigateToCategory(val id: Long): CategoryEffect
}