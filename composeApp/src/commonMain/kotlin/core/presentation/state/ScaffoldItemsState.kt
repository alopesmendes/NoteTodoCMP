package core.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import core.presentation.navigation.Routes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.StringResource

@Immutable
data class ScaffoldItemsState(
    val currentRoute: Routes? = null,
    val floatingActionButtonVisible: Boolean = false,
    val floatingActionButtonIcon: ImageVector? = null,
    val floatingActionButtonText: StringResource? = null,
    val topBarActions: ImmutableList<ImageVector> = persistentListOf(),
    val bottomBarItems: ImmutableList<Routes> = persistentListOf(),
)
