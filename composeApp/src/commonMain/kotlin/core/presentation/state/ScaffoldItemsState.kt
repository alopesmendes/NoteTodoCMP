package core.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import core.presentation.navigation.Routes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import org.jetbrains.compose.resources.StringResource


typealias OnRouteChange = (Routes) -> Unit

typealias OnFloatingActionButtonClick = () -> Unit

typealias OnTopBarActionClick = () -> Unit

@Immutable
data class ScaffoldItemsState(
    val currentRoute: Routes = Routes.Tasks,
    val onRouteChange: OnRouteChange = {},
    val onFloatingActionButtonClick: OnFloatingActionButtonClick = {},
    val floatingActionButtonVisible: Boolean = false,
    val floatingActionButtonIcon: ImageVector? = null,
    val floatingActionButtonText: StringResource? = null,
    val topBarActions: ImmutableMap<ImageVector, OnTopBarActionClick> = persistentMapOf(),
    val bottomBarItems: ImmutableList<Routes> = persistentListOf(),
)
