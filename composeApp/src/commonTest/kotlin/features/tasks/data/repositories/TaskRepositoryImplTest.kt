package features.tasks.data.repositories

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import features.tasks.data.datasources.TaskDatasource
import features.tasks.data.mapper.mapToCreateTaskDto
import features.tasks.data.mapper.mapToTask
import features.tasks.data.mapper.mapToTaskList
import features.tasks.data.mapper.mapToUpdateTaskDto
import features.tasks.data.models.PriorityDto
import features.tasks.data.models.StatusDto
import features.tasks.data.models.TaskDto
import features.tasks.domain.entities.CreateTask
import features.tasks.domain.entities.Priority
import features.tasks.domain.entities.Status
import features.tasks.domain.entities.UpdateTask
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
class TaskRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined
    private lateinit var taskDatasource: TaskDatasource
    private lateinit var taskRepository: TaskRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        taskDatasource = mock()
        taskRepository = TaskRepositoryImpl(taskDatasource)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get all tasks successfully when data is available`() = runTest {
        // given:
        val tasks = List(10) {
            TaskDto(
                id = it.toLong(),
                title = "Title $it",
                description = "Description $it",
                priority = PriorityDto.MEDIUM,
                status = StatusDto.IN_PROGRESS,
                categoryId = null,
            )
        }

        // when:
        every { taskDatasource.findTasks() } returns tasks
        val actual = taskRepository.getTasks()

        // then:
        val expected = Result.success(tasks.mapToTaskList())
        verify(exactly(1)) { taskDatasource.findTasks() }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should fails to get all tasks when request throws exception`() = runTest {
        // given:
        val exception = Exception()

        // when:
        every { taskDatasource.findTasks() } throws exception
        val actual = taskRepository.getTasks()

        // then:
        verify(exactly(1)) { taskDatasource.findTasks() }
        assertThat(actual).isEqualTo(Result.failure(exception))
    }

    @Test
    fun `should get task by id successfully when data is available`() = runTest {
        // given:
        val id = 1L
        val taskDto = TaskDto(
            id = 1L,
            title = "Title 1",
            description = "Description 1",
            priority = PriorityDto.MEDIUM,
            status = StatusDto.IN_PROGRESS,
            categoryId = null,
        )

        // when:
        every { taskDatasource.findTaskById(id) } returns taskDto
        val actual = taskRepository.getTaskById(id)

        // then:
        val expected = Result.success(taskDto.mapToTask())
        verify(exactly(1)) { taskDatasource.findTaskById(any()) }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should fail to get task by id when request throws exception`() = runTest {
        // given:
        val id = 1L
        val exception = Exception()

        // when:
        every { taskDatasource.findTaskById(id) } throws exception
        val actual = taskRepository.getTaskById(id)

        // then:
        verify(exactly(1)) { taskDatasource.findTaskById(any()) }
        assertThat(actual).isEqualTo(Result.failure(exception))
    }

    @Test
    fun `should create task successfully when data creating request is successful`() = runTest {
        // given:
        val createTask = CreateTask(
            title = "fames",
            description = null,
            priority = Priority.MEDIUM,
            status = Status.IN_PROGRESS,
            categoryId = null
        )
        val createTaskDto = createTask.mapToCreateTaskDto()

        // when:
        every { taskDatasource.createTask(createTaskDto) } returns Unit
        val actual = taskRepository.createTask(createTask)


        // then:
        verify(exactly(1)) { taskDatasource.createTask(any()) }

        assertThat(actual).isEqualTo(Result.success(Unit))

    }

    @Test
    fun `should fail to create task when request throws exception`() = runTest {
        // given:
        val exception = Exception()
        val createTask = CreateTask(
            title = "fames",
            description = null,
            priority = Priority.MEDIUM,
            status = Status.IN_PROGRESS,
            categoryId = null
        )
        val createTaskDto = createTask.mapToCreateTaskDto()

        // when:
        every { taskDatasource.createTask(createTaskDto) } throws exception
        val actual = taskRepository.createTask(createTask)

        // then:
        verify(exactly(1)) { taskDatasource.createTask(any()) }

        assertThat(actual).isEqualTo(Result.failure(exception))
    }

    @Test
    fun `should update task successfully when data updating request is successful`() = runTest {
        // given:
        val id = 1L
        val updateTask = UpdateTask(
            id = id,
            title = "fames",
            description = null,
            priority = Priority.MEDIUM,
            status = Status.IN_PROGRESS,
            categoryId = null,
        )
        val updateTaskDto = updateTask.mapToUpdateTaskDto()

        // when:
        every { taskDatasource.updateTask(updateTaskDto) } returns Unit
        val actual = taskRepository.updateTask(updateTask)

        // then:
        verify(exactly(1)) { taskDatasource.updateTask(any()) }

        assertThat(actual).isEqualTo(Result.success(Unit))
    }

    @Test
    fun `should fail to update task when request throws exception`() = runTest {
        // given:
        val exception = Exception()
        val updateTask = UpdateTask(
            id = 1L,
            title = "fames",
            description = null,
            priority = Priority.MEDIUM,
            status = Status.IN_PROGRESS,
        )
        val updateTaskDto = updateTask.mapToUpdateTaskDto()

        // when:
        every { taskDatasource.updateTask(updateTaskDto) } throws exception
        val actual = taskRepository.updateTask(updateTask)

        // then:
        verify(exactly(1)) { taskDatasource.updateTask(any()) }

        assertThat(actual).isEqualTo(Result.failure(exception))
    }

    @Test
    fun `should delete task successfully when data deleting request is successful`() = runTest {
        // given:
        val id = 1L

        // when:
        every { taskDatasource.deleteTask(id) } returns Unit
        val actual = taskRepository.deleteTask(id)

        // then:
        verify(exactly(1)) { taskDatasource.deleteTask(any()) }

        assertThat(actual).isEqualTo(Result.success(Unit))
    }

    @Test
    fun `should fail to delete task when request throws exception`() = runTest {
        // given:
        val id = 1L
        val exception = Exception()

        // when:
        every { taskDatasource.deleteTask(id) } throws exception
        val actual = taskRepository.deleteTask(id)

        // then:
        verify(exactly(1)) { taskDatasource.deleteTask(any()) }

        assertThat(actual).isEqualTo(Result.failure(exception))
    }
}