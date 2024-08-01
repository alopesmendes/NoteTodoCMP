package features.tasks.data.mapper

import features.tasks.data.models.CreateTaskDto
import features.tasks.data.models.PriorityDto
import features.tasks.data.models.StatusDto
import features.tasks.data.models.TaskDto
import features.tasks.data.models.UpdateTaskDto
import features.tasks.domain.entities.CreateTask
import features.tasks.domain.entities.Priority
import features.tasks.domain.entities.Status
import features.tasks.domain.entities.Task
import features.tasks.domain.entities.UpdateTask
import org.ailtontech.notetodo.database.TaskEntity

fun TaskEntity.mapToTaskDto(): TaskDto = TaskDto(
    id = id,
    title = title,
    description = description,
    status = status,
    priority = priority,
    categoryId = category_id,
)

fun List<TaskEntity>.mapToTaskDtoList(): List<TaskDto> = map { it.mapToTaskDto() }

fun StatusDto.mapToStatus(): Status = when (this) {
    StatusDto.TODO -> Status.TODO
    StatusDto.IN_PROGRESS -> Status.IN_PROGRESS
    StatusDto.DONE -> Status.DONE
}

fun Status.mapToStatusDto(): StatusDto = when (this) {
    Status.TODO -> StatusDto.TODO
    Status.IN_PROGRESS -> StatusDto.IN_PROGRESS
    Status.DONE -> StatusDto.DONE
}

fun PriorityDto.mapToPriority(): Priority = when (this) {
    PriorityDto.LOWEST -> Priority.LOWEST
    PriorityDto.LOW -> Priority.LOW
    PriorityDto.MEDIUM -> Priority.MEDIUM
    PriorityDto.HIGH -> Priority.HIGH
    PriorityDto.HIGHEST -> Priority.HIGHEST
}

fun Priority.mapToPriorityDto(): PriorityDto = when (this) {
    Priority.LOWEST -> PriorityDto.LOWEST
    Priority.LOW -> PriorityDto.LOW
    Priority.MEDIUM -> PriorityDto.MEDIUM
    Priority.HIGH -> PriorityDto.HIGH
    Priority.HIGHEST -> PriorityDto.HIGHEST
}

fun TaskDto.mapToTask(): Task = Task(
    id = id,
    title = title,
    description = description,
    status = status.mapToStatus(),
    priority = priority.mapToPriority(),
    categoryId = categoryId
)

fun List<TaskDto>.mapToTaskList(): List<Task> = map { it.mapToTask() }

fun CreateTask.mapToCreateTaskDto(): CreateTaskDto = CreateTaskDto(
    title = title,
    description = description,
    status = status.mapToStatusDto(),
    priority = priority.mapToPriorityDto(),
    categoryId = categoryId
)

fun UpdateTask.mapToUpdateTaskDto(): UpdateTaskDto = UpdateTaskDto(
    id = id,
    title = title,
    description = description,
    status = status?.mapToStatusDto(),
    priority = priority?.mapToPriorityDto(),
    categoryId = categoryId
)