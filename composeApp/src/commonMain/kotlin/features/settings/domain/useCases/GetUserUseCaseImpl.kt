package features.settings.domain.useCases

import core.utils.State
import features.settings.domain.entities.User
import features.settings.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetUserUseCaseImpl(
    private val settingsRepository: SettingsRepository,
): GetUserUseCase {
    override fun invoke(): Flow<State<User>> = flow {
        emit(State.Loading)
        try {
            val flowState = settingsRepository.getUser().map { result ->
                result.fold(
                    onSuccess = {
                        State.Success(it)
                    },
                    onFailure = {
                        State.Error(it.message ?: "")
                        }
                )
            }.catch {
                emit(State.Error(it.message ?: ""))
            }
            emitAll(flowState)
        }   catch (e: Exception) {
            emit(State.Error(e.message ?: ""))

        }
    }

}