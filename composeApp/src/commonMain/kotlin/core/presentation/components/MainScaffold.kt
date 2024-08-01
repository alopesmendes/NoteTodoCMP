package core.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.state.ScaffoldItemsState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    scaffoldItemsState: ScaffoldItemsState,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeContent,
        modifier = modifier,
        content = content,
        bottomBar = {
            MainBottomBar(
                currentRoute = scaffoldItemsState.currentRoute,
                onRouteChange = scaffoldItemsState.onRouteChange,
                bottomItems = scaffoldItemsState.bottomBarItems,
            )
        },
        topBar = {
            MainTopBar(
                modifier = Modifier.fillMaxWidth(),
                actions = scaffoldItemsState.topBarActions
            )
        },
        floatingActionButton = {
            MainFloatingActionButton(
                isFloatingActionButtonVisible = scaffoldItemsState.floatingActionButtonVisible,
                onFloatingActionButtonClick = scaffoldItemsState.onFloatingActionButtonClick,
                floatingActionButtonIcon = scaffoldItemsState.floatingActionButtonIcon,
                floatingActionButtonTextId = scaffoldItemsState.floatingActionButtonText
            )
        }
    )
}

@Preview
@Composable
fun MainScaffoldPreview() {
    MainScaffold(
        scaffoldItemsState = ScaffoldItemsState(),
        content = {
            Text("Hello world")
        }
    )
}