package features.categories.data.mapper

import features.categories.data.models.CategoryDto
import org.ailtontech.notetodo.database.CategoryEntity

fun CategoryEntity.mapToCategoryDto(): CategoryDto = CategoryDto(
    id = id,
    name = name,
    color = color,
)

fun List<CategoryEntity>.mapToCategoryDtoList(): List<CategoryDto> = map { it.mapToCategoryDto() }