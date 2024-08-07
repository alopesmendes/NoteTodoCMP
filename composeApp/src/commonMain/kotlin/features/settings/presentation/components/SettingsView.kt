package features.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsView(
    modifier: Modifier = Modifier,
    firstname: String,
    lastname: String,
    nickname: String,
    onFirstnameChange: (String) -> Unit,
    onLastnameChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstname,
            onValueChange = { onFirstnameChange(it) },
            label = { Text("First Name") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastname,
            onValueChange = { onLastnameChange(it) },
            label = { Text("Last Name") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nickname,
            onValueChange = { onNicknameChange(it) },
            label = { Text("Nickname") }
        )
    }
}