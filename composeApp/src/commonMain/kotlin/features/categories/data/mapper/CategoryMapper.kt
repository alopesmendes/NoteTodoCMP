package features.categories.data.mapper

import features.categories.data.models.CategoryDto
import features.categories.data.models.CreateCategoryDto
import features.categories.data.models.UpdateCategoryDto
import features.categories.domain.entities.Category
import features.categories.domain.entities.CreateCategory
import features.categories.domain.entities.UpdateCategory
import org.ailtontech.notetodo.database.CategoryEntity

fun CategoryEntity.mapToCategoryDto(): CategoryDto = CategoryDto(
    id = id,
    name = name,
    color = color,
)

fun List<CategoryEntity>.mapToCategoryDtoList(): List<CategoryDto> = map { it.mapToCategoryDto() }

fun CategoryDto.mapToCategory(): Category = Category(
    id = id,
    name = name,
    color = color,
)

fun List<CategoryDto>.mapToCategoryList(): List<Category> = map { it.mapToCategory() }

fun CreateCategory.mapToCreateCategoryDto(): CreateCategoryDto = CreateCategoryDto(
    name = name,
    color = color,
)

fun UpdateCategory.mapToUpdateCategoryDto(): UpdateCategoryDto = UpdateCategoryDto(
    id = id,
    name = name,
    color = color,
)