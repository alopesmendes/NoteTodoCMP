package features.settings.presentation.viewModels

import core.utils.BaseViewModel
import features.settings.presentation.reducers.SettingsEffect
import features.settings.presentation.reducers.SettingsIntent
import features.settings.presentation.reducers.SettingsReducer
import features.settings.presentation.reducers.state.SettingsState

class SettingsViewModel(
    settingsReducer: SettingsReducer
): BaseViewModel<SettingsState, SettingsIntent, SettingsEffect>(
    initialState = SettingsState(),
    reducer = settingsReducer
) {
    init {
        sendIntent(SettingsIntent.GetUser)
    }
}