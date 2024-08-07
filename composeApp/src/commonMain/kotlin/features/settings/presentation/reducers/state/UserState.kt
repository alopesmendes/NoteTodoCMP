package features.settings.presentation.reducers.state

import androidx.compose.runtime.Immutable

@Immutable
data class UserState(
    val firstname: String = "",
    val lastname: String = "",
    val nickname: String = "",
)
