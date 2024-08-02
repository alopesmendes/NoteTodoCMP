package features.categories.presentation.reducers

import core.utils.Reducer
import features.categories.domain.useCases.GetCategoriesUseCase
import features.categories.presentation.mapper.mapToCategoryState
import features.categories.presentation.reducers.state.CategoryState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class CategoryReducer(
    private val getCategoriesUseCase: GetCategoriesUseCase,
): Reducer<CategoryState, CategoryIntent, CategoryEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(CategoryState) -> CategoryState, Unit>,
        intent: CategoryIntent,
        sendIntent: (CategoryIntent) -> Unit,
        sendEffect: (CategoryEffect) -> Unit
    ) {
        when (intent) {
            CategoryIntent.FetchCategories -> {
                getCategoriesUseCase().collectLatest { state ->
                    updateState.invoke(state::mapToCategoryState)
                }
            }
        }
    }
}