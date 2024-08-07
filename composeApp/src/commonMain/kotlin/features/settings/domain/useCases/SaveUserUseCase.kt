package features.settings.domain.useCases

import core.utils.State
import features.settings.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface SaveUserUseCase {
    operator fun invoke(user: User): Flow<State<Unit>>
}