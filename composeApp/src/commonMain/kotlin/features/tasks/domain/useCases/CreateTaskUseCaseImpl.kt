package features.tasks.domain.useCases

import core.utils.State
import features.tasks.domain.entities.CreateTask
import features.tasks.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateTaskUseCaseImpl(
    private val taskRepository: TaskRepository
): CreateTaskUseCase {
    override fun invoke(createTask: CreateTask): Flow<State<Unit>> = flow {
        emit(State.Loading)

        try {
            val result = taskRepository.createTask(createTask)
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