package di

import android.provider.ContactsContract.Data
import core.DatastorePreferencesFactory
import core.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single {
        DriverFactory(
            androidContext().applicationContext
        )
    }

    single {
        DatastorePreferencesFactory(
            androidContext().applicationContext
        )
    }
}