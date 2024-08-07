package features.settings.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.presentation.navigation.Routes
import core.presentation.state.ScaffoldItemsState
import features.settings.presentation.components.SettingsContent
import features.settings.presentation.reducers.SettingsIntent
import features.settings.presentation.viewModels.SettingsViewModel
import notetodo.composeapp.generated.resources.Res
import notetodo.composeapp.generated.resources.save_note
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = koinViewModel(),
    scaffoldItemsState: ScaffoldItemsState,
    onScaffoldItemsState: (ScaffoldItemsState) -> Unit,
) {
    val state by settingsViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        onScaffoldItemsState(
            scaffoldItemsState.copy(
                currentRoute = Routes.Settings,
                floatingActionButtonVisible = true,
                floatingActionButtonText = Res.string.save_note,
                floatingActionButtonIcon = Icons.Outlined.Add,
                onFloatingActionButtonClick = {
                    settingsViewModel.sendIntent(
                        SettingsIntent.SaveUser(
                            firstname = state.firstname,
                            lastname = state.lastname,
                            nickname = state.nickname,
                        )
                    )
                },
            )
        )
    }

    SettingsContent(
        modifier = modifier,
        state = state,
        onSettingsStateChange = {
            settingsViewModel.sendIntent(
                SettingsIntent.UpdateUser(
                    firstname = it.firstname,
                    lastname = it.lastname,
                    nickname = it.nickname,
                )
            )
        }
    )
}