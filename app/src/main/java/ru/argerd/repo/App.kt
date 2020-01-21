package ru.argerd.repo

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.argerd.repo.model.NetworkApi

class App : Application() {
    private lateinit var retrofit: Retrofit

    companion object {
        var api: NetworkApi? = null
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        retrofit = Retrofit.Builder()
                .baseUrl("https://mentoring-35da2.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(NetworkApi::class.java)
    }
}