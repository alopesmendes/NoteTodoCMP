package features.categories.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import features.categories.presentation.reducers.state.CategoryStateItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryListView(
    modifier: Modifier = Modifier,
    categories: ImmutableList<CategoryStateItem>,
    onCategoryClick: (CategoryStateItem) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(categories, key = { it.id }) {
            CategoryListItem(
                modifier = Modifier.fillMaxWidth(),
                categoryStateItem = it,
                onClick = { onCategoryClick(it) },
            )
        }
    }
}

@Preview
@Composable
fun CategoryListViewPreview() {
    CategoryListView(
        categories = (1..10).map {
            CategoryStateItem(
                id = it.toLong(),
                name = "Category $it",
                color = Color.Green,
            )
        }.toPersistentList(),
        onCategoryClick = {}
    )
}