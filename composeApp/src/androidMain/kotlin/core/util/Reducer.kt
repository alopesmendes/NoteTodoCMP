package core.util

import kotlin.reflect.KFunction1

interface Reducer<State : Reducer.ViewState, Intent : Reducer.ViewIntent, Effect : Reducer.ViewEffect> {
    interface ViewState

    interface ViewIntent

    interface ViewEffect

    suspend fun reduce(
        updateState: KFunction1<(State) -> State, Unit>,
        intent: Intent,
        sendIntent: (Intent) -> Unit,
        sendEffect: (Effect) -> Unit,
    )
}