package features.tasks.presentation.viewModels

import core.utils.BaseViewModel
import features.tasks.presentation.reducers.TasksEffect
import features.tasks.presentation.reducers.TasksIntent
import features.tasks.presentation.reducers.TasksReducer
import features.tasks.presentation.reducers.state.TasksState

class TasksViewModel(
    reducer: TasksReducer,
): BaseViewModel<TasksState, TasksIntent, TasksEffect>(
    initialState = TasksState(),
    reducer = reducer,
) {
    init {
        sendIntent(TasksIntent.FetchTasks)
    }
}