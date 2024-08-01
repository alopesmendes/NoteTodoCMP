package features.tasks.domain.entities

data class UpdateTask(
    val id: Long,
    val title: String? = null,
    val description: String? = null,
    val priority: Priority? = null,
    val status: Status? = null,
    val categoryId: Long? = null,
)
