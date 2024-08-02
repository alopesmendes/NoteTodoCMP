package features.categories.data.datasources

import features.categories.data.models.CategoryDto
import features.categories.data.models.CreateCategoryDto
import features.categories.data.models.UpdateCategoryDto
import kotlinx.coroutines.flow.Flow

interface CategoryDatasource {
    fun findCategories(): Flow<List<CategoryDto>>

    fun findCategoryById(id: Long): Flow<CategoryDto>

    suspend fun createCategory(category: CreateCategoryDto)

    suspend fun updateCategory(category: UpdateCategoryDto)

    suspend fun deleteCategory(id: Long)
}