package features.settings.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.components.ErrorComponent
import core.presentation.components.LoadingComponent
import features.settings.presentation.reducers.state.SettingsState

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    state: SettingsState,
    onSettingsStateChange: (SettingsState) -> Unit,
) {
    when {
        state.isLoading -> {
            LoadingComponent(
                modifier = modifier.fillMaxSize()
            )
        }

        state.error != null -> {
            ErrorComponent(
                modifier = modifier.fillMaxSize(),
                error = state.error,
            )
        }

        else -> {
            SettingsView(
                modifier = modifier,
                firstname = state.firstname,
                lastname = state.lastname,
                nickname = state.nickname,
                onLastnameChange = {
                    onSettingsStateChange(
                        state.copy(
                            lastname = it
                        )
                    )
                },
                onNicknameChange = {
                    onSettingsStateChange(
                        state.copy(
                            nickname = it
                        )
                    )
                },
                onFirstnameChange = {
                    onSettingsStateChange(
                        state.copy(
                            firstname = it,
                        )
                    )
                }
            )
        }
    }
}