package rusmammals.data.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import rusmammals.data.model.UserPreferences

private object PreferencesKeys {
    val TOKEN = stringPreferencesKey("token")
}

interface PreferencesDataSource {
    val flow: Flow<UserPreferences>

    suspend fun updateToken(token: String)

    suspend fun getToken(): String?

    suspend fun logout()
}

class PreferencesDataSourceImpl(private val dataStore: DataStore<Preferences>) :
    PreferencesDataSource {

    override val flow: Flow<UserPreferences> = dataStore.data.map { preferences ->
        val token = preferences[PreferencesKeys.TOKEN]

        UserPreferences(token)
    }

    override suspend fun updateToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOKEN] = token
        }
    }

    override suspend fun getToken(): String? {
        val prefs = dataStore.data.firstOrNull() ?: return null
        return prefs[PreferencesKeys.TOKEN]
    }

    override suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}