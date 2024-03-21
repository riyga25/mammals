package rusmammals.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import rusmammals.data.BuildConfig
import rusmammals.data.network.NetworkApi
import rusmammals.data.network.NetworkDataSource
import rusmammals.data.prefs.PreferencesDataSource
import rusmammals.data.prefs.PreferencesDataSourceImpl
import rusmammals.data.repository.DataRepositoryImpl
import rusmammals.domain.repository.DataRepository
import java.util.concurrent.TimeUnit

val dataModule = module {
    single { providePreferenceStorage(androidContext()) }
    single { provideHttpClient() }
    single { provideRetrofit(get()) }

    single { NetworkDataSource(get(), get()) }
    single<PreferencesDataSource> { PreferencesDataSourceImpl(get()) }

    single<DataRepository> { DataRepositoryImpl(get(), get()) }

    single<NetworkApi> { getApi(get()) }
}

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

private fun provideRetrofit(
    client: OkHttpClient
): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .baseUrl("https://rusmam.ru/")
        .client(client)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}

internal fun provideHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder().apply {
        connectTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        }
    }

    return client.build()
}

private fun providePreferenceStorage(context: Context): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile("user_preferences") }
    )
}

internal inline fun <reified T> getApi(retrofit: Retrofit): T = retrofit.create(T::class.java)

