package features.tasks.data.repositories

import features.tasks.data.datasources.TaskDatasource
import features.tasks.data.mapper.mapToCreateTaskDto
import features.tasks.data.mapper.mapToTask
import features.tasks.data.mapper.mapToTaskList
import features.tasks.data.mapper.mapToUpdateTaskDto
import features.tasks.domain.entities.CreateTask
import features.tasks.domain.entities.Task
import features.tasks.domain.entities.UpdateTask
import features.tasks.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDatasource: TaskDatasource
): TaskRepository {
    override fun getTasks(): Flow<Result<List<Task>>> {
        return try {
            taskDatasource
                .findTasks()
                .map { Result.success(it.mapToTaskList()) }
                .catch { emit(Result.failure(it)) }
        } catch (e: Exception) {
            flowOf(Result.failure(e))
        }
    }

    override fun getTaskById(id: Long): Flow<Result<Task>> {
        return try {
            taskDatasource
                .findTaskById(id)
                .map { Result.success(it.mapToTask()) }
                .catch { emit(Result.failure(it)) }
        } catch (e: Exception) {
            flowOf(Result.failure(e))
        }
    }

    override suspend fun createTask(
        createTask: CreateTask,
    ): Result<Unit> {
        return try {
            taskDatasource.createTask(createTask.mapToCreateTaskDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateTask(
        updateTask: UpdateTask,
    ): Result<Unit> {
        return try {
            taskDatasource.updateTask(updateTask.mapToUpdateTaskDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteTask(id: Long): Result<Unit> {
        return try {
            taskDatasource.deleteTask(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}