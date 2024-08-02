package di

import features.categories.presentation.reducers.CategoryReducer
import features.tasks.presentation.reducers.TasksReducer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val reducerModule = module {
    singleOf(::TasksReducer)

    singleOf(::CategoryReducer)
}