package features.tasks.domain.useCases

import core.utils.State
import kotlinx.coroutines.flow.Flow

interface DeleteTaskUseCase {
    operator fun invoke(id: Long): Flow<State<Unit>>
}