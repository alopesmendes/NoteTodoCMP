package features.categories.presentation.screens

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
import features.categories.presentation.components.CategoryContent
import features.categories.presentation.reducers.CategoryEffect
import features.categories.presentation.viewModels.CategoryViewModel
import notetodo.composeapp.generated.resources.Res
import notetodo.composeapp.generated.resources.save_category
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    categoryViewModel: CategoryViewModel = koinViewModel(),
    scaffoldItemsState: ScaffoldItemsState,
    onScaffoldItemsStateChange: (ScaffoldItemsState) -> Unit,
) {
    val state by categoryViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        onScaffoldItemsStateChange(
            scaffoldItemsState.copy(
                currentRoute = Routes.Categories,
                bottomBarItems = bottomBarItems,
                floatingActionButtonIcon = Icons.Outlined.Add,
                floatingActionButtonText = Res.string.save_category,
                floatingActionButtonVisible = true,
                onFloatingActionButtonClick = { },
            )
        )
    }

    CategoryContent(
        modifier = modifier,
        state = state,
        onCategoryClick = {
            categoryViewModel.sendEffect(
                CategoryEffect.NavigateToCategory(it)
            )
        }
    )

}