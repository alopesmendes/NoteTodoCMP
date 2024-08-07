package core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import notetodo.composeapp.generated.resources.Res
import notetodo.composeapp.generated.resources.cancel
import notetodo.composeapp.generated.resources.confirm
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainDialog(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    content: @Composable () -> Unit,
    onConfirm: () -> Unit = {},
    onVisibilityChange: (Boolean) -> Unit = {},
) {
    AnimatedVisibility(
        visible = isVisible
    ) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { onVisibilityChange(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                        onVisibilityChange(false)
                    },
                    content = {
                        Text(stringResource(Res.string.confirm))
                    }
                )
            },
            dismissButton = {
                TextButton(
                    onClick = { onVisibilityChange(false) },
                    content = {
                        Text(stringResource(Res.string.cancel))
                    }
                )
            },
            title = content,
        )
    }
}

@Preview
@Composable
fun MainDialogPreview() {
    MainDialog(
        isVisible = true,
        content = {
            Text("Dialog")
        }
    )
}