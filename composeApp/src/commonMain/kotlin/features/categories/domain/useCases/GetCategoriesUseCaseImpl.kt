package features.categories.domain.useCases

import core.utils.State
import features.categories.domain.entities.Category
import features.categories.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetCategoriesUseCaseImpl(
    private val categoryRepository: CategoryRepository
) : GetCategoriesUseCase {
    override fun invoke(): Flow<State<List<Category>>> = flow {
        emit(State.Loading)

        try {
            val state = categoryRepository
                .getCategories()
                .map { result ->
                    result.fold(
                        onSuccess = { State.Success(it) },
                        onFailure = { State.Error(it.message ?: "") }
                    )
                }

            emitAll(state)
        } catch (e: Exception) {
            emit(State.Error(e.message ?: ""))
        }
    }
}