package features.tasks.domain.useCases

import core.utils.State
import features.tasks.domain.entities.Task
import kotlinx.coroutines.flow.Flow

interface GetTasksUseCase {
    operator fun invoke(): Flow<State<List<Task>>>
}