package features.settings.presentation.reducers.state

import androidx.compose.runtime.Immutable
import core.utils.Reducer

@Immutable
data class SettingsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val firstname: String = "",
    val lastname: String = "",
    val nickname: String = "",
): Reducer.ViewState
