package features.tasks.domain.useCases

import core.utils.State
import features.tasks.domain.entities.CreateTask
import kotlinx.coroutines.flow.Flow

interface CreateTaskUseCase {
    operator fun invoke(createTask: CreateTask): Flow<State<Unit>>
}