package features.tasks.domain.entities

data class CreateTask(
    val title: String,
    val description: String?,
    val priority: Priority,
    val status: Status,
    val categoryId: Long?,
)
