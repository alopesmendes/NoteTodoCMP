package di

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
}