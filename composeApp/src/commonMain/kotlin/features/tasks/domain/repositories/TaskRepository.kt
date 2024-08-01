package features.tasks.domain.repositories

import features.tasks.domain.entities.CreateTask
import features.tasks.domain.entities.Task
import features.tasks.domain.entities.UpdateTask
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<Result<List<Task>>>

    fun getTaskById(id: Long): Flow<Result<Task>>

    suspend fun createTask(createTask: CreateTask): Result<Unit>

    suspend fun updateTask(updateTask: UpdateTask): Result<Unit>

    suspend fun deleteTask(id: Long): Result<Unit>
}