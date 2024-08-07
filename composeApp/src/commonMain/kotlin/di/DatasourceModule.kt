package di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import core.DatastorePreferencesFactory
import core.createDatabase
import core.utils.Constants.DATASTORE_FILENAME
import features.categories.data.datasources.CategoryDatasource
import features.categories.data.datasources.CategoryLocalDatasource
import features.tasks.data.datasources.TaskDatasource
import features.tasks.data.datasources.TaskLocalDatasourceImpl
import org.ailtontech.notetodo.database.NoteDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val datasourceModule = module {
    single<NoteDatabase> {
        createDatabase(get())
    }

    single<DataStore<Preferences>> {
        get<DatastorePreferencesFactory>().create(DATASTORE_FILENAME)
    }

    single<TaskDatasource> { TaskLocalDatasourceImpl(get()) }

    single<CategoryDatasource> { CategoryLocalDatasource(get(), get(named("IO_DISPATCHER"))) }
}