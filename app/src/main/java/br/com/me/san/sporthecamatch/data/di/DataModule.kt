package br.com.me.san.sporthecamatch.data.di

import android.util.Log
import br.com.me.san.sporthecamatch.data.repositories.RetornoRepository
import br.com.me.san.sporthecamatch.data.repositories.RetornoRepositoryImpl
import br.com.me.san.sporthecamatch.data.services.RetornoService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    private const val  OK_HTTP = "OkHttp"

    fun load() {
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.d(OK_HTTP, it)
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<RetornoService>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<RetornoRepository> { RetornoRepositoryImpl(get())  } //Criar repositories
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://sportsmatch.com.br/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}