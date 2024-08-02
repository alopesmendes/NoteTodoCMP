package features.tasks.presentation.reducers

import core.utils.Reducer
import features.tasks.domain.useCases.GetTasksUseCase
import features.tasks.presentation.mapper.mapToTasksState
import features.tasks.presentation.reducers.state.TasksState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class TasksReducer(
    private val getTasksUseCase: GetTasksUseCase,
): Reducer<TasksState, TasksIntent, TasksEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(TasksState) -> TasksState, Unit>,
        intent: TasksIntent,
        sendIntent: (TasksIntent) -> Unit,
        sendEffect: (TasksEffect) -> Unit
    ) {
        when (intent) {
            is TasksIntent.FetchTasks -> {
                getTasksUseCase().collectLatest { state ->
                    updateState.invoke(state::mapToTasksState)
                }
            }
        }
    }
}