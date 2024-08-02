package features.categories.data.models

data class UpdateCategoryDto(
    val id: Long,
    val name: String? = null,
    val color: String? = null,
)
