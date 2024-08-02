package features.tasks.domain.useCases

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.utils.State
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import features.tasks.domain.entities.Priority
import features.tasks.domain.entities.Status
import features.tasks.domain.entities.Task
import features.tasks.domain.repositories.TaskRepository
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
class GetTasksUseCaseImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var taskRepository: TaskRepository
    private lateinit var getTasksUseCase: GetTasksUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        taskRepository = mock()
        getTasksUseCase = GetTasksUseCaseImpl(taskRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get all tasks successfully when data is available`() = runTest {
        // given:
        val tasks = List(10) {
            Task(
                id = it.toLong(),
                title = "Title $it",
                description = "Description $it",
                priority = Priority.MEDIUM,
                status = Status.IN_PROGRESS,
                categoryId = null
            )
        }
        val result = Result.success(tasks)
        val flowResult = flowOf(result)

        // when:
        every { taskRepository.getTasks() } returns flowResult
        val actual = getTasksUseCase()

        // then:
        actual.test {
            verify(exactly(1)) { taskRepository.getTasks() }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(tasks))

            awaitComplete()
        }
    }

    @Test
    fun `should fails to get all tasks when request fails`() = runTest {
        // given:
        val exception = Exception()
        val result = Result.failure<List<Task>>(exception)
        val flowResult = flowOf(result)

        // when:
        every { taskRepository.getTasks() } returns flowResult
        val actual = getTasksUseCase()

        // then:
        actual.test {
            verify(exactly(1)) { taskRepository.getTasks() }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.message ?: ""))

            awaitComplete()
        }
    }
}