package features.categories.data.datasources

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import features.categories.data.mapper.mapToCategoryDto
import features.categories.data.mapper.mapToCategoryDtoList
import features.categories.data.models.CategoryDto
import features.categories.data.models.CreateCategoryDto
import features.categories.data.models.UpdateCategoryDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.ailtontech.notetodo.database.NoteDatabase

class CategoryLocalDatasource(
    database: NoteDatabase,
    private val dispatcher: CoroutineDispatcher
): CategoryDatasource {
    private val query = database.categoryEntityQueries

    override fun findCategories(): Flow<List<CategoryDto>> = query
        .selectAllCategories()
        .asFlow()
        .mapToList(dispatcher)
        .map { it.mapToCategoryDtoList() }

    override fun findCategoryById(id: Long): Flow<CategoryDto> = query
        .selectCategoryById(id)
        .asFlow()
        .mapToOne(dispatcher)
        .map { it.mapToCategoryDto() }

    override suspend fun createCategory(category: CreateCategoryDto) {
        withContext(dispatcher) {
            query.insertCategory(
                name = category.name,
                color = category.color,
            )
        }
    }

    override suspend fun updateCategory(category: UpdateCategoryDto) {
        withContext(dispatcher) {
            query.updateCategory(
                id = category.id,
                name = category.name,
                color = category.color,
            )
        }
    }

    override suspend fun deleteCategory(id: Long) {
        withContext(dispatcher) {
            query.deleteCategory(id)
        }
    }
}