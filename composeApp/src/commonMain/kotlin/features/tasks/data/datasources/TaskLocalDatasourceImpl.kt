package features.tasks.data.datasources

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import features.tasks.data.mapper.mapToTaskDto
import features.tasks.data.mapper.mapToTaskDtoList
import features.tasks.data.models.CreateTaskDto
import features.tasks.data.models.TaskDto
import features.tasks.data.models.UpdateTaskDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.ailtontech.notetodo.database.NoteDatabase

class TaskLocalDatasourceImpl(
    database: NoteDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
): TaskDatasource {
    private val query = database.taskEntityQueries
    override fun findTasks(): Flow<List<TaskDto>> = query
        .selectAllTasks()
        .asFlow()
        .mapToList(dispatcher)
        .map { it.mapToTaskDtoList() }

    override fun findTaskById(id: Long): Flow<TaskDto> = query
        .selectTaskById(id)
        .asFlow()
        .mapToOne(dispatcher)
        .map { it.mapToTaskDto() }

    override suspend fun createTask(task: CreateTaskDto) {
        withContext(dispatcher) {
            query.insertTask(
                title = task.title,
                description = task.description,
                category_id = task.categoryId,
                priority = task.priority,
                status = task.statusDto,
            )
        }
    }

    override suspend fun updateTask(task: UpdateTaskDto) {
        withContext(dispatcher) {
            query.updateTask(
                title = task.title,
                description = task.description,
                category_id = task.categoryId,
                priority = task.priority?.name,
                status = task.status?.name,
                id = task.id,
            )
        }
    }

    override suspend fun deleteTask(id: Long) {
        withContext(dispatcher) {
            query.deleteTask(id)
        }
    }


}