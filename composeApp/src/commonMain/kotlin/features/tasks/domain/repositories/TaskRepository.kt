package features.tasks.domain.repositories

import features.tasks.domain.entities.CreateTask
import features.tasks.domain.entities.Task
import features.tasks.domain.entities.UpdateTask
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Result<List<Task>>

    fun getTaskById(id: Long): Result<Task>

    fun createTask(createTask: CreateTask): Result<Unit>

    fun updateTask(updateTask: UpdateTask): Result<Unit>

    fun deleteTask(id: Long): Result<Unit>
}