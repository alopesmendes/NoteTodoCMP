package core.utils

sealed interface State<out T> {
    data object Loading: State<Nothing>

    data class Success<T>(val data: T): State<T>

    data class Error(val message: String): State<Nothing>
}