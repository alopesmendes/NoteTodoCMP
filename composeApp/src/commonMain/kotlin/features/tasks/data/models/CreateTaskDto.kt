package features.tasks.data.models

data class CreateTaskDto(
    val title: String,
    val description: String?,
    val priority: PriorityDto,
    val statusDto: StatusDto,
    val categoryId: Long?,
)
