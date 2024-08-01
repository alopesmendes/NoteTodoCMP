package core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Routes,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.fullRoute()
    ) {
        composable(route = Routes.Notes.fullRoute(), arguments = Routes.Notes.navParams()) {

        }

        composable(route = Routes.Categories.fullRoute(), arguments = Routes.Categories.navParams()) {

        }

        composable(route = Routes.Settings.fullRoute(), arguments = Routes.Settings.navParams()) {

        }
    }
}