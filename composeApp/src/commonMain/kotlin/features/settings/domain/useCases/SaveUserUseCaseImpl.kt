package features.settings.domain.useCases

import core.utils.State
import features.settings.domain.entities.User
import features.settings.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveUserUseCaseImpl(
    private val settingsRepository: SettingsRepository,
): SaveUserUseCase {
    override fun invoke(user: User): Flow<State<Unit>> = flow {
        emit(State.Loading)
        try {
            val result = settingsRepository.saveUser(user)
            val state = result.fold(
                onSuccess = {
                    State.Success(it)
                },
                onFailure = {
                    State.Error(it.message ?: "")
                }
            )
            emit(state)
        } catch (e: Exception) {
            emit(State.Error(e.message ?: ""))
        }
    }

}