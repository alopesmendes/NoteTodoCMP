package features.tasks.domain.useCases

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
import features.tasks.domain.repositories.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class DeleteTaskUseCaseImplTest{
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var taskRepository: TaskRepository
    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        taskRepository = mock()
        deleteTaskUseCase = DeleteTaskUseCaseImpl(taskRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get all tasks successfully when data is available`() = runTest {
        // given:
        val id = 1L
        val result = Result.success(Unit)

        // when:
        everySuspend { taskRepository.deleteTask(id) } returns result
        val actual = deleteTaskUseCase(id)

        // then:
        actual.test {
            verifySuspend(exactly(1)) { taskRepository.deleteTask(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(Unit))

            awaitComplete()
        }
    }

    @Test
    fun `should fails to get all tasks when request fails`() = runTest {
        // given:
        val id = 1L
        val exception = Exception()
        val result = Result.failure<Unit>(exception)

        // when:
        everySuspend { taskRepository.deleteTask(id) } returns result
        val actual = deleteTaskUseCase(id)

        // then:
        actual.test {
            verifySuspend(exactly(1)) { taskRepository.deleteTask(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.message ?: ""))

            awaitComplete()
        }
    }
}