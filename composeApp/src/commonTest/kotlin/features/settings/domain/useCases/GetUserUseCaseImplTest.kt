package features.settings.domain.useCases

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.utils.State
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import features.settings.domain.entities.User
import features.settings.domain.repositories.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetUserUseCaseImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getUserUseCase: GetUserUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        settingsRepository = mock()
        getUserUseCase = GetUserUseCaseImpl(settingsRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get user successfully when data is available`() = runTest {
        // given:
        val user = User(
            firstname = "Ailton",
            lastname = "Lopes",
            nickname = "ailtonlopes",
        )
        val result = Result.success(user)

        // when:
        every { settingsRepository.getUser() } returns flowOf(result)
        val actual = getUserUseCase()

        // then:
        actual.test {
            verify(exactly(1)) { settingsRepository.getUser() }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(user))

            awaitComplete()
        }
    }

    @Test
    fun `should fails to save user when request fails`() = runTest {
        // given:
        val exception = Exception()
        val result = Result.failure<User>(exception)

        // when:
        every { settingsRepository.getUser() } returns flowOf(result)
        val actual = getUserUseCase()

        // then:
        actual.test {
            verify(exactly(1)) { settingsRepository.getUser() }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.message ?: ""))

            awaitComplete()
        }
    }
}