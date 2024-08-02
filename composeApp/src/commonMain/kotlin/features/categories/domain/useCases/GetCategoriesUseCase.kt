package features.categories.domain.useCases

import core.utils.State
import features.categories.domain.entities.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
    operator fun invoke(): Flow<State<List<Category>>>
}