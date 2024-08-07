package features.settings.presentation.mapper

import core.utils.State
import features.settings.domain.entities.User
import features.settings.presentation.reducers.state.SettingsState
import features.settings.presentation.reducers.state.UserState

fun User.mapToUserState(): UserState = UserState(
    firstname = firstname,
    lastname = lastname,
    nickname = nickname,
)

fun State<User>.mapToUserState(settingsState: SettingsState): SettingsState {
    return when (this) {
        is State.Error -> settingsState.copy(error = message, isLoading = false)
        State.Loading -> settingsState.copy(isLoading = true)
        is State.Success -> settingsState.copy(user = data.mapToUserState(), isLoading = false)
    }
}

fun State<Unit>.mapToUserState(settingsState: SettingsState): SettingsState {
    return when (this) {
        is State.Error -> settingsState.copy(error = message, isLoading = false)
        State.Loading -> settingsState.copy(isLoading = true)
        is State.Success -> settingsState.copy(isLoading = false)
    }
}