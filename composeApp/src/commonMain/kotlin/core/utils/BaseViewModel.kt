package core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Reducer.ViewState, Intent : Reducer.ViewIntent, Effect : Reducer.ViewEffect>(
    initialState: State,
    private val reducer: Reducer<State, Intent, Effect>
) : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _intents: MutableSharedFlow<Intent> = MutableSharedFlow()

    private val _effects = Channel<Effect>(capacity = Channel.CONFLATED)
    val effects = _effects.receiveAsFlow()

    init {
        onHandleIntents()
    }

    fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            _effects.send(effect)
        }
    }

    fun sendIntent(intent: Intent) {
        viewModelScope.launch {
            _intents.emit(intent)
        }
    }

    private fun onHandleIntents() {
        viewModelScope.launch {
            _intents.collectLatest {
                reducer.reduce(
                    updateState = _state::update,
                    intent = it,
                    sendIntent = ::sendIntent,
                    sendEffect = ::sendEffect,
                )
            }
        }
    }
}