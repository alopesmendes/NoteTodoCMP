package features.settings.data.models

import androidx.datastore.preferences.core.stringPreferencesKey

data class UserDto(
    val firstname: String,
    val lastname: String,
    val nickname: String,
) {
    companion object {
        val firstnameKey = stringPreferencesKey("firstname")
        val lastnameKey = stringPreferencesKey("lastname")
        val nicknameKey = stringPreferencesKey("nickname")
    }
}