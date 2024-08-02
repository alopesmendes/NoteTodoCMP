package features.tasks.data.models

data class UpdateTaskDto(
    val id: Long,
    val title: String? = null,
    val description: String? = null,
    val priority: PriorityDto? = null,
    val status: StatusDto? = null,
    val categoryId: Long? = null,
)
