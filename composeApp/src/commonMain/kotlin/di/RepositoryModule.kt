package di

import features.categories.data.repositories.CategoryRepositoryImpl
import features.categories.domain.repositories.CategoryRepository
import features.tasks.data.repositories.TaskRepositoryImpl
import features.tasks.domain.repositories.TaskRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }

    singleOf(::CategoryRepositoryImpl) { bind<CategoryRepository>() }
}