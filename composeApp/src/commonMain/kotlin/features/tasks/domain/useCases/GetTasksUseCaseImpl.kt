package features.tasks.domain.useCases

import core.utils.State
import features.tasks.domain.entities.Task
import features.tasks.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetTasksUseCaseImpl(
    private val taskRepository: TaskRepository
): GetTasksUseCase {
    override operator fun invoke(): Flow<State<List<Task>>> = flow {
        emit(State.Loading)

        try {
            val tasksFlow = taskRepository.getTasks().map { result ->
                result.fold(
                    onSuccess = {
                        State.Success(it)
                    },
                    onFailure = {
                        State.Error(it.message ?: "")
                    },
                )
            }
            emitAll(tasksFlow)
        } catch (e: Exception) {
            emit(State.Error(e.message ?: ""))
        }
    }
}