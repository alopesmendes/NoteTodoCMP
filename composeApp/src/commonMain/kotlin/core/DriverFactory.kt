package core

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import org.ailtontech.notetodo.database.NoteDatabase
import org.ailtontech.notetodo.database.TaskEntity

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): NoteDatabase {
    val driver = driverFactory.createDriver()
    val database = NoteDatabase(
        driver = driver,
        taskEntityAdapter = TaskEntity.Adapter(
            priorityAdapter = EnumColumnAdapter(),
            statusAdapter = EnumColumnAdapter(),
        )
    )
    NoteDatabase.Schema.create(driver)
    return database
}