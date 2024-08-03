package core.utils

sealed interface State<out T> {
    data object Loading: State<Nothing>

    data class Success<T>(val data: T): State<T>

    data class Error(val message: String): State<Nothing>
}

fun <T> State<T>.onSuccess(block: (T) -> Unit): Unit {
    if (this is State.Success) {
        block(data)
    }
}