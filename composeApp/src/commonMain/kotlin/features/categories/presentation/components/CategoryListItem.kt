package features.categories.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import features.categories.presentation.reducers.state.CategoryStateItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryListItem(
    modifier: Modifier = Modifier,
    categoryStateItem: CategoryStateItem,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        elevation = 4.dp,
        backgroundColor = categoryStateItem.color,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = categoryStateItem.name,
                style = MaterialTheme.typography.h6,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

@Preview
@Composable
fun CategoryListItemPreview() {
    CategoryListItem(
        categoryStateItem = CategoryStateItem(
            id = 1,
            name = "Work",
            color = Color.Green,
        ),
        onClick = {},
    )
}