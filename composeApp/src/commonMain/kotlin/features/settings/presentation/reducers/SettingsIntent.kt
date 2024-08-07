package features.settings.presentation.reducers

import androidx.compose.runtime.Immutable
import core.utils.Reducer

@Immutable
sealed interface SettingsIntent: Reducer.ViewIntent {
    data object GetUser: SettingsIntent

    data class UpdateUser(
        val firstname: String? = null,
        val lastname: String? = null,
        val nickname: String? = null,
    ): SettingsIntent

    data class SaveUser(
        val firstname: String = "",
        val lastname: String = "",
        val nickname: String = "",
    ): SettingsIntent
}