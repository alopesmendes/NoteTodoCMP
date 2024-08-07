package features.settings.presentation.mapper

import core.utils.State
import features.settings.domain.entities.User
import features.settings.presentation.reducers.state.SettingsState
import kotlin.jvm.JvmName

@JvmName("UserStateMapToUserStateUser")
fun State<User>.mapToUserState(settingsState: SettingsState): SettingsState {
    return when (this) {
        is State.Error -> settingsState.copy(error = message, isLoading = false)
        State.Loading -> settingsState.copy(isLoading = true)
        is State.Success -> settingsState.copy(
            firstname = data.firstname,
            lastname = data.lastname,
            nickname = data.nickname,
            isLoading = false
        )
    }
}

@JvmName("UnitMapToUserStateUser")
fun State<Unit>.mapToUserState(settingsState: SettingsState): SettingsState {
    return when (this) {
        is State.Error -> settingsState.copy(error = message, isLoading = false)
        State.Loading -> settingsState.copy(isLoading = true)
        is State.Success -> settingsState.copy(isLoading = false)
    }
}