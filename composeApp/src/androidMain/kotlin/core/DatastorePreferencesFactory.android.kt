package core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual class DatastorePreferencesFactory(
    private val context: Context
) {
    actual fun create(dataStoreFileName: String): DataStore<Preferences> = createDataStore {
        context.filesDir.resolve(dataStoreFileName).absolutePath
    }
}