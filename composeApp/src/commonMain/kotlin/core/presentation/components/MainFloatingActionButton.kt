package core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import core.presentation.state.OnFloatingActionButtonClick
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainFloatingActionButton(
    modifier: Modifier = Modifier,
    isFloatingActionButtonVisible: Boolean,
    onFloatingActionButtonClick: OnFloatingActionButtonClick,
    floatingActionButtonTextId: StringResource? = null,
    floatingActionButtonIcon: ImageVector? = null,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isFloatingActionButtonVisible,
    ) {
        ExtendedFloatingActionButton(
            onClick = onFloatingActionButtonClick,
            text = {
                floatingActionButtonTextId?.let {
                    Text(stringResource(it))
                }
            },
            icon = if (floatingActionButtonIcon != null) {
                {
                    Icon(
                        floatingActionButtonIcon,
                        contentDescription = null,
                    )
                }
            } else {
                null
            }
        )
    }
}

@Preview
@Composable
fun MainFloatingActionButtonPreview() {
    MainFloatingActionButton(
        isFloatingActionButtonVisible = true,
        onFloatingActionButtonClick = {}
    )
}