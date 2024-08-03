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
import io.github.aakira.napier.Napier

class TaskRepositoryImpl(
    private val taskDatasource: TaskDatasource
): TaskRepository {
    override fun getTasks(): Result<List<Task>> {
        return try {
            val tasks = taskDatasource
                .findTasks()
                .mapToTaskList()
            Result.success(tasks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getTaskById(id: Long): Result<Task> {
        return try {
            val task = taskDatasource.findTaskById(id)
            if (task != null) {
                Result.success(task.mapToTask())
            } else {
                Result.failure(IllegalStateException("Task not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun createTask(
        createTask: CreateTask,
    ): Result<Unit> {
        return try {
            taskDatasource.createTask(createTask.mapToCreateTaskDto())
            Napier.i("Task created")
            Result.success(Unit)
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            Result.failure(e)
        }
    }

    override fun updateTask(
        updateTask: UpdateTask,
    ): Result<Unit> {
        return try {
            taskDatasource.updateTask(updateTask.mapToUpdateTaskDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun deleteTask(id: Long): Result<Unit> {
        return try {
            taskDatasource.deleteTask(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}