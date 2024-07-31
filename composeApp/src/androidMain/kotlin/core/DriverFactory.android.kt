package core

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import core.utils.Constants.DATABASE_NAME
import org.ailtontech.notetodo.database.NoteDatabase

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            context = context,
            name = DATABASE_NAME,
            schema = NoteDatabase.Schema,
        )
    }
}