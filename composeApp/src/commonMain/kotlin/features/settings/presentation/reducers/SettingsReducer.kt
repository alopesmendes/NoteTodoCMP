package features.settings.presentation.reducers

import core.utils.Reducer
import features.settings.domain.entities.User
import features.settings.domain.useCases.GetUserUseCase
import features.settings.domain.useCases.SaveUserUseCase
import features.settings.presentation.mapper.mapToUserState
import features.settings.presentation.reducers.state.SettingsState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class SettingsReducer(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
): Reducer<SettingsState, SettingsIntent, SettingsEffect>{
    override suspend fun reduce(
        updateState: KFunction1<(SettingsState) -> SettingsState, Unit>,
        intent: SettingsIntent,
        sendIntent: (SettingsIntent) -> Unit,
        sendEffect: (SettingsEffect) -> Unit
    ) {
        when (intent) {
            SettingsIntent.GetUser -> {
                getUserUseCase().collectLatest { state ->
                    updateState.invoke(state::mapToUserState)
                }
            }
            is SettingsIntent.UpdateUser -> {
                updateState.invoke { state ->
                    state.copy(
                        isLoading = false,
                        firstname = intent.firstname ?: state.firstname,
                        lastname = intent.lastname ?: state.lastname,
                        nickname = intent.nickname ?: state.nickname,
                    )
                }
            }

            is SettingsIntent.SaveUser -> {
                saveUserUseCase(
                    user = User(
                        firstname = intent.firstname,
                        lastname = intent.lastname,
                        nickname = intent.nickname,
                    )
                ).collectLatest { state ->
                    updateState.invoke(state::mapToUserState)
                }
            }
        }
    }

}