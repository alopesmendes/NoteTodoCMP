package features.tasks.data.datasources

import features.tasks.data.mapper.mapToTaskDto
import features.tasks.data.mapper.mapToTaskDtoList
import features.tasks.data.models.CreateTaskDto
import features.tasks.data.models.TaskDto
import features.tasks.data.models.UpdateTaskDto
import io.github.aakira.napier.Napier
import org.ailtontech.notetodo.database.NoteDatabase

class TaskLocalDatasourceImpl(
    database: NoteDatabase,
): TaskDatasource {
    private val query = database.taskEntityQueries
    override fun findTasks(): List<TaskDto> = query
        .selectAllTasks()
        .executeAsList()
        .mapToTaskDtoList()

    override fun findTaskById(id: Long): TaskDto? = query
        .selectTaskById(id)
        .executeAsOneOrNull()
        ?.mapToTaskDto()

    override fun createTask(task: CreateTaskDto) {
        try {
            query.insertTask(
                title = task.title,
                description = task.description,
                category_id = task.categoryId,
                priority = task.priority,
                status = task.status,
            )
            Napier.i("Task created")
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            throw e
        }
    }

    override fun updateTask(task: UpdateTaskDto) {
        query.updateTask(
            title = task.title,
            description = task.description,
            category_id = task.categoryId,
            priority = task.priority?.name,
            status = task.status?.name,
            id = task.id,
        )
    }

    override fun deleteTask(id: Long) {
        query.deleteTask(id)
    }


}