package features.settings.domain.repositories

import features.settings.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getUser(): Flow<Result<User>>

    suspend fun saveUser(user: User): Result<Unit>
}