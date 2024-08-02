package features.categories.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.components.ErrorComponent
import core.presentation.components.LoadingComponent
import features.categories.presentation.reducers.state.CategoryState

@Composable
fun CategoryContent(
    modifier: Modifier = Modifier,
    state: CategoryState,
    onCategoryClick: (id: Long) -> Unit,
) {
    when {
        state.isLoading -> {
            LoadingComponent(modifier = modifier)
        }

        state.error != null -> {
            ErrorComponent(
                modifier = modifier,
                error = state.error,
            )
        }
        else -> {
            CategoryListView(
                modifier = modifier,
                categories = state.categories,
                onCategoryClick = { onCategoryClick(it.id) },
            )
        }
    }
}