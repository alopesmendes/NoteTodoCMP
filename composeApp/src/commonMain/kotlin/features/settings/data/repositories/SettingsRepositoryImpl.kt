package features.settings.data.repositories

import features.settings.data.datasources.SettingsDatasource
import features.settings.data.mapper.mapToUser
import features.settings.data.mapper.mapToUserDto
import features.settings.domain.entities.User
import features.settings.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val settingsDatasource: SettingsDatasource,
): SettingsRepository {
    override fun getUser(): Flow<Result<User>> {
        return try {
            settingsDatasource.getUser().map {
                Result.success(it.mapToUser())
            }
        } catch (e: Exception) {
            flowOf(Result.failure(e))
        }
    }

    override suspend fun saveUser(user: User): Result<Unit> {
        return try {
            settingsDatasource.saveUser(user.mapToUserDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}