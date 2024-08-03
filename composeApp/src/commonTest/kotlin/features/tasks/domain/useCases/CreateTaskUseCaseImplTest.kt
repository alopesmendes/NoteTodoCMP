package features.tasks.domain.useCases

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.utils.State
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import features.tasks.domain.entities.CreateTask
import features.tasks.domain.entities.Priority
import features.tasks.domain.entities.Status
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
class CreateTaskUseCaseImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var taskRepository: TaskRepository
    private lateinit var createTaskUseCase: CreateTaskUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        taskRepository = mock()
        createTaskUseCase = CreateTaskUseCaseImpl(taskRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get all tasks successfully when data is available`() = runTest {
        // given:
        val createTask = CreateTask(
            title = "title",
            description = "description",
            priority = Priority.LOW,
            status = Status.TODO,
            categoryId = null,
        )
        val result = Result.success(Unit)

        // when:
        every { taskRepository.createTask(createTask) } returns result
        val actual = createTaskUseCase(createTask)

        // then:
        actual.test {
            verify(exactly(1)) { taskRepository.createTask(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(Unit))

            awaitComplete()
        }
    }

    @Test
    fun `should fails to get all tasks when request fails`() = runTest {
        // given:
        val exception = Exception()
        val createTask = CreateTask(
            title = "title",
            description = "description",
            priority = Priority.LOW,
            status = Status.TODO,
            categoryId = null,
        )
        val result = Result.failure<Unit>(exception)

        // when:
        every { taskRepository.createTask(createTask) } returns result
        val actual = createTaskUseCase(createTask)

        // then:
        actual.test {
            verify(exactly(1)) { taskRepository.createTask(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.message ?: ""))

            awaitComplete()
        }
    }
}