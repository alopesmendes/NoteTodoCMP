package core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import notetodo.composeapp.generated.resources.Res
import notetodo.composeapp.generated.resources.categories_title
import notetodo.composeapp.generated.resources.notes_title
import notetodo.composeapp.generated.resources.settings_title
import org.jetbrains.compose.resources.StringResource

@Immutable
sealed class Routes(
    val stringRes: StringResource? = null,
    val icon: ImageVector? = null,
    protected val route: String,
    private val params: ImmutableList<Pair<String, NavType<*>>> = persistentListOf(),
) {
    fun fullRoute(): String = "$route${
        params.map { it.first }.joinToString(
            separator = "/",
            prefix = "/",
            transform = { "{$it}" }
        )
    }"

    fun navParams(): List<NamedNavArgument> = params.map {
        navArgument(it.first) {
            type = it.second
        }
    }

    fun navigateTo(vararg params: Any): String = "$route${
        params.joinToString(
            separator = "/",
            prefix = "/"
        )
    }"

    data object Notes: Routes(
        route = "notes",
        icon = Icons.AutoMirrored.Outlined.List,
        stringRes = Res.string.notes_title
    )

    data object Categories: Routes(
        route = "categories",
        icon = Icons.Outlined.Category,
        stringRes = Res.string.categories_title
    )

    data object Settings: Routes(
        route = "settings",
        icon = Icons.Outlined.Settings,
        stringRes = Res.string.settings_title,
    )
}

val bottomBarItems = persistentListOf(
    Routes.Notes,
    Routes.Categories,
    Routes.Settings,
)