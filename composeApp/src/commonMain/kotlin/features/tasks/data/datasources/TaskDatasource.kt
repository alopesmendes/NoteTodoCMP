package features.tasks.data.datasources

import features.tasks.data.models.CreateTaskDto
import features.tasks.data.models.TaskDto
import features.tasks.data.models.UpdateTaskDto
import kotlinx.coroutines.flow.Flow

interface TaskDatasource {
    fun findTasks(): Flow<List<TaskDto>>

    fun findTaskById(id: Long): Flow<TaskDto>

    suspend fun createTask(task: CreateTaskDto)

    suspend fun updateTask(task: UpdateTaskDto)

    suspend fun deleteTask(id: Long)
}