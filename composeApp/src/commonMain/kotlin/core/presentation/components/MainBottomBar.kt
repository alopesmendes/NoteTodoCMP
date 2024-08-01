package core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.navigation.Routes
import core.presentation.navigation.bottomBarItems
import core.presentation.state.OnRouteChange
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: Routes? = null,
    bottomItems: ImmutableList<Routes>,
    onRouteChange: OnRouteChange,
) {
    AnimatedVisibility(
        visible = bottomItems.isNotEmpty()
    ) {
        BottomNavigation(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground
        ) {
            bottomItems.forEach { route ->
                BottomNavigationItem(
                    icon = {
                        route.icon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null
                            )
                        }
                    },
                    onClick = {
                        onRouteChange(route)
                    },
                    selected = currentRoute == route,
                    label = if (route.stringRes != null) {
                        {
                           Text(stringResource(route.stringRes))
                        }
                    } else {
                        null
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onBackground
                )
            }
        }
    }

}

@Preview
@Composable
fun MainBottomBarPreview() {
    MainBottomBar(
        bottomItems = bottomBarItems,
        onRouteChange = {}
    )
}