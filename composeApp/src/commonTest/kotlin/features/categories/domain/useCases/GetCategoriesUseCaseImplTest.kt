package features.categories.domain.useCases

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.utils.State
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import features.categories.domain.entities.Category
import features.categories.domain.repositories.CategoryRepository
import features.tasks.domain.entities.Priority
import features.tasks.domain.entities.Status
import features.tasks.domain.entities.Task
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
class GetCategoriesUseCaseImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var categoryRepository: CategoryRepository
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        categoryRepository = mock()
        getCategoriesUseCase = GetCategoriesUseCaseImpl(categoryRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get all tasks successfully when data is available`() = runTest {
        // given:
        val categories = List(10) {
            Category(
                id = it.toLong(),
                name = "Title $it",
                color = "0xFF000000"
            )
        }
        val result = Result.success(categories)
        val flowResult = flowOf(result)

        // when:
        every { categoryRepository.getCategories() } returns flowResult
        val actual = getCategoriesUseCase()

        // then:
        actual.test {
            verify(exactly(1)) { categoryRepository.getCategories() }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(categories))

            awaitComplete()
        }
    }

    @Test
    fun `should fails to get all tasks when request fails`() = runTest {
        // given:
        val exception = Exception()
        val result = Result.failure<List<Category>>(exception)
        val flowResult = flowOf(result)

        // when:
        every { categoryRepository.getCategories() } returns flowResult
        val actual = getCategoriesUseCase()

        // then:
        actual.test {
            verify(exactly(1)) { categoryRepository.getCategories() }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.message ?: ""))

            awaitComplete()
        }
    }
}