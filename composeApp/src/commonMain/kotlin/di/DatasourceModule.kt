package di

import core.createDatabase
import features.tasks.data.datasources.TaskDatasource
import features.tasks.data.datasources.TaskLocalDatasourceImpl
import org.ailtontech.notetodo.database.NoteDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val datasourceModule = module {
    single<NoteDatabase> {
        createDatabase(get())
    }

    single<TaskDatasource> { TaskLocalDatasourceImpl(get(), get(named("IO_DISPATCHER"))) }
}