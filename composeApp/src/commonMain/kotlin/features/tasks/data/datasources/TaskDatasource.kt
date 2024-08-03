package features.tasks.data.datasources

import features.tasks.data.models.CreateTaskDto
import features.tasks.data.models.TaskDto
import features.tasks.data.models.UpdateTaskDto
import kotlinx.coroutines.flow.Flow

interface TaskDatasource {
    fun findTasks(): List<TaskDto>

    fun findTaskById(id: Long): TaskDto?

    fun createTask(task: CreateTaskDto)

    fun updateTask(task: UpdateTaskDto)

    fun deleteTask(id: Long)
}