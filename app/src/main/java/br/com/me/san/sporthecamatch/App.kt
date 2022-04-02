package br.com.me.san.sporthecamatch

import android.app.Application
import br.com.me.san.sporthecamatch.data.di.DataModule
import br.com.me.san.sporthecamatch.domain.DomainModule
import br.com.me.san.sporthecamatch.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin { androidContext(this@App) }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()

    }


}