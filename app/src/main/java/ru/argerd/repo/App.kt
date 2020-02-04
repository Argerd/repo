package ru.argerd.repo

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import ru.argerd.repo.di.component.AppComponent
import ru.argerd.repo.di.component.DaggerAppComponent

class App : Application() {

    companion object {
        var firstOpenCategory = true
        var firstOpenEventsNews = true
        lateinit var context: Context

        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        component = DaggerAppComponent.create()

        context = this
    }
}