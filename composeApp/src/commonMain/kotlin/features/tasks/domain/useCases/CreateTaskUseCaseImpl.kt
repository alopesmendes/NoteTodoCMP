package features.tasks.domain.useCases

import core.utils.State
import features.tasks.domain.entities.CreateTask
import features.tasks.domain.repositories.TaskRepository
import io.github.aakira.napier.Napier
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
            Napier.i("State: $state")
            emit(state)
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            emit(State.Error(e.message ?: ""))
        }
    }
}