package di

import features.tasks.data.datasources.TaskDatasource
import features.tasks.data.datasources.TaskLocalDatasourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val datasourceModule = module {
    singleOf(::TaskLocalDatasourceImpl) { bind<TaskDatasource>() }
}