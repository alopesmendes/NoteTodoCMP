package core

import app.cash.sqldelight.db.SqlDriver
import org.ailtontech.notetodo.database.NoteDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): NoteDatabase {
    val driver = driverFactory.createDriver()
    val database = NoteDatabase(driver)
    NoteDatabase.Schema.create(driver)
    return database
}