package di

import features.categories.domain.useCases.GetCategoriesUseCase
import features.categories.domain.useCases.GetCategoriesUseCaseImpl
import features.tasks.domain.useCases.GetTasksUseCase
import features.tasks.domain.useCases.GetTasksUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::GetTasksUseCaseImpl) { bind<GetTasksUseCase>() }

    singleOf(::GetCategoriesUseCaseImpl) { bind<GetCategoriesUseCase>() }
}