package features.categories.domain.repositories

import features.categories.domain.entities.Category
import features.categories.domain.entities.CreateCategory
import features.categories.domain.entities.UpdateCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<Result<List<Category>>>

    fun getCategoryById(id: Long): Flow<Result<Category>>

    suspend fun createCategory(category: CreateCategory): Result<Unit>

    suspend fun updateCategory(category: UpdateCategory): Result<Unit>

    suspend fun deleteCategory(id: Long): Result<Unit>
}