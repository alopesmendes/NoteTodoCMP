package features.categories.data.repositories

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
import features.categories.data.datasources.CategoryDatasource
import features.categories.data.mapper.mapToCategory
import features.categories.data.mapper.mapToCategoryList
import features.categories.data.mapper.mapToCreateCategoryDto
import features.categories.data.mapper.mapToUpdateCategoryDto
import features.categories.data.models.CategoryDto
import features.categories.domain.entities.CreateCategory
import features.categories.domain.entities.UpdateCategory
import features.categories.domain.repositories.CategoryRepository
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
class CategoryRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var categoryDatasource: CategoryDatasource
    private lateinit var categoryRepository: CategoryRepository

    @BeforeTest
    fun setup() {
        categoryDatasource = mock()
        categoryRepository = CategoryRepositoryImpl(categoryDatasource)
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get all tasks successfully when data is available`() = runTest {
        // given:
        val categories = List(10) {
            CategoryDto(
                id = it.toLong(),
                name = "Title $it",
                color = "0xFF000000",
            )
        }
        val result = flowOf(categories)

        // when:
        every { categoryDatasource.findCategories() } returns result
        val actual = categoryRepository.getCategories()

        // then:
        val expected = Result.success(categories.mapToCategoryList())
        actual.test {
            verify(exactly(1)) { categoryDatasource.findCategories() }

            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `should fails to get all tasks when request throws exception`() = runTest {
        // given:
        val exception = Exception()

        // when:
        every { categoryDatasource.findCategories() } throws exception
        val actual = categoryRepository.getCategories()

        // then:
        actual.test {
            verify(exactly(1)) { categoryDatasource.findCategories() }

            assertThat(awaitItem()).isEqualTo(Result.failure(exception))
            awaitComplete()
        }
    }

    @Test
    fun `should get task by id successfully when data is available`() = runTest {
        // given:
        val id = 1L
        val categoryDto = CategoryDto(
            id = id,
            name = "Title 1",
            color = "0xFF000000"
        )
        val result = flowOf(categoryDto)

        // when:
        every { categoryDatasource.findCategoryById(id) } returns result
        val actual = categoryRepository.getCategoryById(id)

        // then:
        val expected = Result.success(categoryDto.mapToCategory())
        actual.test {
            verify(exactly(1)) { categoryDatasource.findCategoryById(any()) }

            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `should fail to get task by id when request throws exception`() = runTest {
        // given:
        val id = 1L
        val exception = Exception()

        // when:
        every { categoryDatasource.findCategoryById(id) } throws exception
        val actual = categoryRepository.getCategoryById(id)

        // then:
        actual.test {
            verify(exactly(1)) { categoryDatasource.findCategoryById(any()) }

            assertThat(awaitItem()).isEqualTo(Result.failure(exception))
            awaitComplete()
        }
    }

    @Test
    fun `should create task successfully when data creating request is successful`() = runTest {
        // given:
        val createTask = CreateCategory(
            name = "Title",
            color = "0xFF000000",
        )
        val createTaskDto = createTask.mapToCreateCategoryDto()

        // when:
        everySuspend { categoryDatasource.createCategory(createTaskDto) } returns Unit
        val actual = categoryRepository.createCategory(createTask)


        // then:
        verifySuspend(exactly(1)) { categoryDatasource.createCategory(any()) }

        assertThat(actual).isEqualTo(Result.success(Unit))

    }

    @Test
    fun `should fail to create task when request throws exception`() = runTest {
        // given:
        val exception = Exception()
        val createTask = CreateCategory(
            name = "Title",
            color = "0xFF000000",
        )
        val createTaskDto = createTask.mapToCreateCategoryDto()

        // when:
        everySuspend { categoryDatasource.createCategory(createTaskDto) } throws exception
        val actual = categoryRepository.createCategory(createTask)

        // then:
        verifySuspend(exactly(1)) { categoryDatasource.createCategory(any()) }

        assertThat(actual).isEqualTo(Result.failure(exception))
    }

    @Test
    fun `should update task successfully when data updating request is successful`() = runTest {
        // given:
        val id = 1L
        val updateTask = UpdateCategory(
            id = id,
            name = "Title",
            color = "0xFF000000",
        )
        val updateTaskDto = updateTask.mapToUpdateCategoryDto()

        // when:
        everySuspend { categoryDatasource.updateCategory(updateTaskDto) } returns Unit
        val actual = categoryRepository.updateCategory(updateTask)

        // then:
        verifySuspend(exactly(1)) { categoryDatasource.updateCategory(any()) }

        assertThat(actual).isEqualTo(Result.success(Unit))
    }

    @Test
    fun `should fail to update task when request throws exception`() = runTest {
        // given:
        val exception = Exception()
        val updateCategory = UpdateCategory(
            id = 1L,
            name = "Title",
            color = "0xFF000000",
        )
        val updateCategoryDto = updateCategory.mapToUpdateCategoryDto()

        // when:
        everySuspend { categoryDatasource.updateCategory(updateCategoryDto) } throws exception
        val actual = categoryRepository.updateCategory(updateCategory)

        // then:
        verifySuspend(exactly(1)) { categoryDatasource.updateCategory(any()) }

        assertThat(actual).isEqualTo(Result.failure(exception))
    }

    @Test
    fun `should delete task successfully when data deleting request is successful`() = runTest {
        // given:
        val id = 1L

        // when:
        everySuspend { categoryDatasource.deleteCategory(id) } returns Unit
        val actual = categoryRepository.deleteCategory(id)

        // then:
        verifySuspend(exactly(1)) { categoryDatasource.deleteCategory(any()) }

        assertThat(actual).isEqualTo(Result.success(Unit))
    }

    @Test
    fun `should fail to delete task when request throws exception`() = runTest {
        // given:
        val id = 1L
        val exception = Exception()

        // when:
        everySuspend { categoryDatasource.deleteCategory(id) } throws exception
        val actual = categoryRepository.deleteCategory(id)

        // then:
        verifySuspend(exactly(1)) { categoryDatasource.deleteCategory(any()) }

        assertThat(actual).isEqualTo(Result.failure(exception))
    }
}