package features.settings.data.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import features.settings.data.models.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SettingsDatastoreDatasource(
    private val dataStore: DataStore<Preferences>
): SettingsDatasource {
    override fun getUser(): Flow<UserDto> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            UserDto(
                firstname = preferences[UserDto.firstnameKey] ?: "",
                lastname = preferences[UserDto.lastnameKey] ?: "",
                nickname = preferences[UserDto.nicknameKey] ?: "",
            )
        }

    override suspend fun saveUser(user: UserDto) {
        dataStore.edit { preferences ->
            preferences[UserDto.firstnameKey] = user.firstname
            preferences[UserDto.lastnameKey] = user.lastname
            preferences[UserDto.nicknameKey] = user.nickname
        }
    }

}