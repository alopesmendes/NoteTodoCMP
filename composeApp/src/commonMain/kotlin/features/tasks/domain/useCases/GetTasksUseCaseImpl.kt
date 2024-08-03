package features.tasks.domain.useCases

import core.utils.State
import features.tasks.domain.entities.Task
import features.tasks.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTasksUseCaseImpl(
    private val taskRepository: TaskRepository
): GetTasksUseCase {
    override operator fun invoke(): Flow<State<List<Task>>> = flow {
        emit(State.Loading)

        try {
            val result = taskRepository.getTasks()
            val state = result.fold(
                onSuccess = {
                    State.Success(it)
                },
                onFailure = {
                    State.Error(it.message ?: "")
                }
            )
            emit(state)
        } catch (e: Exception) {
            emit(State.Error(e.message ?: ""))
        }
    }
}