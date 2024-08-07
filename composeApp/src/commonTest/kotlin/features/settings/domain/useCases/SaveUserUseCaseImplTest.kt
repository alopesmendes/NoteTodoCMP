package features.settings.domain.useCases

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.utils.State
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import features.settings.domain.entities.User
import features.settings.domain.repositories.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SaveUserUseCaseImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var saveUserUseCase: SaveUserUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        settingsRepository = mock()
        saveUserUseCase = SaveUserUseCaseImpl(settingsRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should save user successfully when data is available`() = runTest {
        // given:
        val user = User(
            firstname = "Ailton",
            lastname = "Lopes",
            nickname = "ailtonlopes",
        )
        val result = Result.success(Unit)

        // when:
        everySuspend { settingsRepository.saveUser(user) } returns result
        val actual = saveUserUseCase(user)

        // then:
        actual.test {
            verifySuspend(exactly(1)) { settingsRepository.saveUser(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(Unit))

            awaitComplete()
        }
    }

    @Test
    fun `should fails to save user when request fails`() = runTest {
        // given:
        val exception = Exception()
        val user = User(
            firstname = "Ailton",
            lastname = "Lopes",
            nickname = "ailtonlopes",
        )
        val result = Result.failure<Unit>(exception)

        // when:
        everySuspend { settingsRepository.saveUser(user) } returns result
        val actual = saveUserUseCase(user)

        // then:
        actual.test {
            verifySuspend(exactly(1)) { settingsRepository.saveUser(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.message ?: ""))

            awaitComplete()
        }
    }
}