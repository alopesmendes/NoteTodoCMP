package core.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import core.presentation.state.ScaffoldItemsState
import features.tasks.presentation.screens.TasksScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    scaffoldItemsState: ScaffoldItemsState,
    onScaffoldItemsState: (ScaffoldItemsState) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = scaffoldItemsState.currentRoute.fullRoute()
    ) {
        composable(route = Routes.Tasks.fullRoute(), arguments = Routes.Tasks.navParams()) {
            TasksScreen(
                modifier = Modifier.fillMaxSize(),
                scaffoldItemsState = scaffoldItemsState,
                onScaffoldItemsState = onScaffoldItemsState,
            )
        }

        composable(route = Routes.Categories.fullRoute(), arguments = Routes.Categories.navParams()) {

        }

        composable(route = Routes.Settings.fullRoute(), arguments = Routes.Settings.navParams()) {

        }
    }
}