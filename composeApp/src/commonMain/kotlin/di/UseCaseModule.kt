package di

import features.categories.domain.useCases.GetCategoriesUseCase
import features.categories.domain.useCases.GetCategoriesUseCaseImpl
import features.settings.domain.useCases.GetUserUseCase
import features.settings.domain.useCases.GetUserUseCaseImpl
import features.settings.domain.useCases.SaveUserUseCase
import features.settings.domain.useCases.SaveUserUseCaseImpl
import features.tasks.domain.useCases.CreateTaskUseCase
import features.tasks.domain.useCases.CreateTaskUseCaseImpl
import features.tasks.domain.useCases.DeleteTaskUseCase
import features.tasks.domain.useCases.DeleteTaskUseCaseImpl
import features.tasks.domain.useCases.GetTasksUseCase
import features.tasks.domain.useCases.GetTasksUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::GetTasksUseCaseImpl) { bind<GetTasksUseCase>() }

    singleOf(::GetCategoriesUseCaseImpl) { bind<GetCategoriesUseCase>() }

    singleOf(::DeleteTaskUseCaseImpl) { bind<DeleteTaskUseCase>() }

    singleOf(::CreateTaskUseCaseImpl) { bind<CreateTaskUseCase>() }

    singleOf(::GetUserUseCaseImpl) { bind<GetUserUseCase>() }

    singleOf(::SaveUserUseCaseImpl) { bind<SaveUserUseCase>() }
}