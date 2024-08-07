package features.tasks.domain.useCases

import core.utils.State
import features.tasks.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteTaskUseCaseImpl(
    private val taskRepository: TaskRepository
): DeleteTaskUseCase {
    override fun invoke(id: Long): Flow<State<Unit>> = flow {
        emit(State.Loading)

        try {
            val result = taskRepository.deleteTask(id)
            val state = result.fold(
                onSuccess = { State.Success(it) },
                onFailure = { State.Error(it.message ?: "") }
            )
            emit(state)
        } catch (e: Exception) {
            emit(State.Error(e.message ?: ""))
        }
    }
}