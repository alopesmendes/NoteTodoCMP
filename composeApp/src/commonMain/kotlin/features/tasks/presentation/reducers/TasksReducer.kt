package features.tasks.presentation.reducers

import core.utils.Reducer
import features.tasks.domain.entities.CreateTask
import features.tasks.domain.entities.Priority
import features.tasks.domain.entities.Status
import features.tasks.domain.useCases.CreateTaskUseCase
import features.tasks.domain.useCases.DeleteTaskUseCase
import features.tasks.domain.useCases.GetTasksUseCase
import features.tasks.presentation.mapper.mapToTasksState
import features.tasks.presentation.reducers.state.TasksState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class TasksReducer(
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
) : Reducer<TasksState, TasksIntent, TasksEffect> {
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

            is TasksIntent.DeleteTask -> {
                deleteTaskUseCase(intent.id).collectLatest { state ->
                    updateState.invoke(state::mapToTasksState)
                    sendIntent(TasksIntent.FetchTasks)
                }
            }

            is TasksIntent.TaskDetailDialogVisibility -> {
                updateState.invoke { it.copy(isDialogVisible = intent.isVisible, isLoading = false) }
            }

            is TasksIntent.CreateTask -> {
                createTaskUseCase(
                    CreateTask(
                        title = intent.title,
                        description = intent.description,
                        priority = Priority.LOW,
                        status = Status.TODO, categoryId = null,
                    )
                ).collectLatest { state ->
                    updateState.invoke(state::mapToTasksState)
                    sendIntent(TasksIntent.FetchTasks)
                }
            }
        }
    }
}