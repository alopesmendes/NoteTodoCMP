package core

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import core.utils.Constants.DATABASE_NAME
import org.ailtontech.notetodo.database.NoteDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = NoteDatabase.Schema, name = DATABASE_NAME)
    }
}