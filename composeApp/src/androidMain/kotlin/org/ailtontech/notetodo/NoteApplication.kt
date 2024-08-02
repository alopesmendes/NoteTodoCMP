package org.ailtontech.notetodo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NoteApplication)

            modules(di.modules)
        }
    }
}