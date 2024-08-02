import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import core.presentation.components.MainScaffold
import core.presentation.navigation.NavigationHost
import core.presentation.navigation.Routes
import core.presentation.state.ScaffoldItemsState
import kotlinx.collections.immutable.persistentMapOf
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            var scaffoldItemsState by remember {
                mutableStateOf(
                    ScaffoldItemsState(
                        onRouteChange = { route, params ->
                            navController.navigate(
                                route.navigateTo(params)
                            )
                        },
                        topBarActions = persistentMapOf(
                            Icons.Outlined.Settings to {
                                navController.navigate(
                                    Routes.Settings.navigateTo()
                                )
                            }
                        )
                    )
                )
            }

            MainScaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldItemsState = scaffoldItemsState,
                content = { paddingValues ->
                    NavigationHost(
                        modifier = Modifier.padding(paddingValues).fillMaxSize(),
                        navController = navController,
                        scaffoldItemsState = scaffoldItemsState,
                        onScaffoldItemsState = { scaffoldItemsState = it }
                    )
                }
            )
        }
    }
}