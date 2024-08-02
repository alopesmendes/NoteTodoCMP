package features.categories.presentation.viewModels

import core.utils.BaseViewModel
import features.categories.presentation.reducers.CategoryEffect
import features.categories.presentation.reducers.CategoryIntent
import features.categories.presentation.reducers.CategoryReducer
import features.categories.presentation.reducers.state.CategoryState

class CategoryViewModel(
    categoryReducer: CategoryReducer
) : BaseViewModel<CategoryState, CategoryIntent, CategoryEffect>(
    initialState = CategoryState(),
    reducer = categoryReducer
) {
    init {
        sendIntent(CategoryIntent.FetchCategories)
    }
}