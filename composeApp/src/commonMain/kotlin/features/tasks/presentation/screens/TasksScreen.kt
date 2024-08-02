package features.tasks.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.presentation.navigation.Routes
import core.presentation.navigation.bottomBarItems
import core.presentation.state.ScaffoldItemsState
import features.tasks.presentation.components.TaskContent
import features.tasks.presentation.viewModels.TasksViewModel
import notetodo.composeapp.generated.resources.Res
import notetodo.composeapp.generated.resources.save_note
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = koinViewModel(),
    scaffoldItemsState: ScaffoldItemsState,
    onScaffoldItemsState: (ScaffoldItemsState) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        onScaffoldItemsState(
            scaffoldItemsState.copy(
                currentRoute = Routes.Tasks,
                bottomBarItems = bottomBarItems,
                floatingActionButtonVisible = true,
                floatingActionButtonText = Res.string.save_note,
                floatingActionButtonIcon = Icons.Outlined.Add,
                onFloatingActionButtonClick = {

                },
            )
        )
    }

    TaskContent(
        modifier = modifier,
        state = state,
        onTaskClick = {

        },
    )
}