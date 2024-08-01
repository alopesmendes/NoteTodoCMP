package core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import core.presentation.state.OnTopBarActionClick
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    actions: ImmutableMap<ImageVector, OnTopBarActionClick>
) {
    AnimatedVisibility(
        visible = actions.isNotEmpty()
    ) {
        TopAppBar(
            modifier = modifier,
            content = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ) {
                    actions.forEach { (icon, onClick) ->
                        IconButton(
                            onClick = onClick
                        ) {
                            Icon(
                                icon,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun MainTopBarPreview() {
    MainTopBar(
        actions = persistentMapOf(
            Icons.Outlined.Settings to {},
        )
    )
}