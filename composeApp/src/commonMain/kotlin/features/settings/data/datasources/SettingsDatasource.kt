package features.settings.data.datasources

import features.settings.data.models.UserDto
import kotlinx.coroutines.flow.Flow

interface SettingsDatasource {
    fun getUser(): Flow<UserDto>

    suspend fun saveUser(user: UserDto)
}