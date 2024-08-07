package features.settings.data.repositories

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import features.settings.data.datasources.SettingsDatasource
import features.settings.data.mapper.mapToUser
import features.settings.data.mapper.mapToUserDto
import features.settings.data.models.UserDto
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
class SettingsRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined
    private lateinit var settingsDatasource: SettingsDatasource
    private lateinit var settingsRepository: SettingsRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        settingsDatasource = mock()
        settingsRepository = SettingsRepositoryImpl(settingsDatasource)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get user successfully when data is available`() = runTest {
        // given:
        val userDto = UserDto(
            firstname = "Ailton",
            lastname = "Lopes",
            nickname = "ailtonlopes",
        )
        val user = userDto.mapToUser()

        // when:
        every { settingsDatasource.getUser() } returns flowOf(userDto)
        val actual = settingsRepository.getUser()

        // then:
        val expected = Result.success(user)
        actual.test {
            verify(exactly(1)) { settingsDatasource.getUser() }
            assertThat(awaitItem()).isEqualTo(expected)

            awaitComplete()
        }
    }

    @Test
    fun `should save user successfully when request is return success`() = runTest {
        // given:
        val user = User(
            firstname = "Ailton",
            lastname = "Lopes",
            nickname = "ailtonlopes",
        )
        val userDto = user.mapToUserDto()

        // when:
        everySuspend { settingsDatasource.saveUser(userDto) } returns Unit
        val actual = settingsRepository.saveUser(user)

        // then:
        val expected = Result.success(Unit)
        verifySuspend(exactly(1)) { settingsDatasource.saveUser(any()) }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should fail save user when request throws exception`() = runTest {
        // given:
        val user = User(
            firstname = "Ailton",
            lastname = "Lopes",
            nickname = "ailtonlopes",
        )
        val userDto = user.mapToUserDto()
        val exception = Exception()

        // when:
        everySuspend { settingsDatasource.saveUser(userDto) } throws exception
        val actual = settingsRepository.saveUser(user)

        // then:
        verifySuspend(exactly(1)) { settingsDatasource.saveUser(any()) }
        assertThat(actual).isEqualTo(Result.failure(exception))
    }
}