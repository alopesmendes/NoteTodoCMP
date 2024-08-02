package features.categories.domain.entities

data class UpdateCategory(
    val id: Long,
    val name: String? = null,
    val color: String? = null,
)
