package features.tasks.domain.entities

data class Task(
    val id: Long,
    val title: String,
    val description: String?,
    val priority: Priority,
    val status: Status,
    val categoryId: Long?,
)
