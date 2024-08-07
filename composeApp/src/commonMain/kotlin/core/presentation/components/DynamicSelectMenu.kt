package core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import core.presentation.state.DropMenuItemState
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : DropMenuItemState> DynamicSelectMenu(
    modifier: Modifier = Modifier,
    selectValue: DropMenuItemState,
    options: ImmutableList<T>,
    label: String,
    onValueChange: (T) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            readOnly = true,
            value = stringResource(selectValue.textId),
            onValueChange = { },
            label = { Text(label) },
            leadingIcon = {
                Icon(
                    selectValue.icon,
                    contentDescription = null,
                    tint = selectValue.color,
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                    onIconClick = { expanded = !expanded },
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(),
        ) {
            options.fastForEach { option ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onValueChange(option)
                    },
                    content = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                option.icon,
                                contentDescription = null,
                                tint = option.color,
                            )
                            Text(text = stringResource(option.textId))
                        }
                    }
                )
            }
        }
    }
}