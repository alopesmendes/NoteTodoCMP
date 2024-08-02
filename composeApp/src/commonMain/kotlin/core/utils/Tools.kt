package core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow

object Tools {
    fun parseToULong(colorHex: String): Long {
        val color = colorHex.removePrefix("#")
        val parsedColor = when (color.length) {
            6 -> color.toLong(16) or 0x00000000FF000000
            8 -> color.toLong(16)
            else -> throw IllegalArgumentException("Unknown color")
        }
        return parsedColor
    }

    @Composable
    fun <T> rememberFlowWithLifecycle(
        flow: Flow<T>,
        lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED
    ): Flow<T> = remember(flow, lifecycle) {
        flow.flowWithLifecycle(
            lifecycle = lifecycle,
            minActiveState = minActiveState
        )
    }
}