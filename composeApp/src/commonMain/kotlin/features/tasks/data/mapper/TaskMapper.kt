package features.tasks.data.mapper

import features.tasks.data.models.TaskDto
import org.ailtontech.notetodo.database.TaskEntity

fun TaskEntity.mapToTaskDto(): TaskDto = TaskDto(
    id = id,
    title = title,
    description = description,
    statusDto = status,
    priority = priority,
    categoryId = category_id,
)

fun List<TaskEntity>.mapToTaskDtoList(): List<TaskDto> = map { it.mapToTaskDto() }