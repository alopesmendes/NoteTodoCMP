package features.categories.data.repositories

import features.categories.data.datasources.CategoryDatasource
import features.categories.data.mapper.mapToCategory
import features.categories.data.mapper.mapToCategoryList
import features.categories.data.mapper.mapToCreateCategoryDto
import features.categories.data.mapper.mapToUpdateCategoryDto
import features.categories.domain.entities.Category
import features.categories.domain.entities.CreateCategory
import features.categories.domain.entities.UpdateCategory
import features.categories.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val categoryDatasource: CategoryDatasource,
): CategoryRepository {
    override fun getCategories(): Flow<Result<List<Category>>> {
        return try {
            categoryDatasource
                .findCategories()
                .map { Result.success(it.mapToCategoryList()) }
                .catch { Result.failure<List<Category>>(it) }
        } catch (e: Exception) {
            flowOf(Result.failure(e))
        }
    }

    override fun getCategoryById(id: Long): Flow<Result<Category>> {
        return try {
            categoryDatasource
                .findCategoryById(id)
                .map { Result.success(it.mapToCategory()) }
                .catch { Result.failure<Category>(it) }
        } catch (e: Exception) {
            flowOf(Result.failure(e))
        }
    }

    override suspend fun createCategory(category: CreateCategory): Result<Unit> {
        return try {
            categoryDatasource.createCategory(category.mapToCreateCategoryDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateCategory(category: UpdateCategory): Result<Unit> {
        return try {
            categoryDatasource.updateCategory(category.mapToUpdateCategoryDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteCategory(id: Long): Result<Unit> {
        return try {
            categoryDatasource.deleteCategory(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}