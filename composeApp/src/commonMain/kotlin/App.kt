import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import core.presentation.components.MainScaffold
import core.presentation.navigation.NavigationHost
import core.presentation.state.ScaffoldItemsState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        var scaffoldItemsState by remember {
            mutableStateOf(
                ScaffoldItemsState(
                    onRouteChange = { route, params ->
                        navController.navigate(
                            route.navigateTo(params)
                        )
                    },
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