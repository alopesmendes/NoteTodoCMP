import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import core.presentation.navigation.NavigationHost
import core.presentation.navigation.Routes
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavigationHost(
            modifier = Modifier.fillMaxSize(),
            startDestination = Routes.Notes,
            navController = navController
        )
    }
}