package di

import core.DatastorePreferencesFactory
import core.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single {
        DriverFactory()
    }

    single {
        DatastorePreferencesFactory()
    }
}