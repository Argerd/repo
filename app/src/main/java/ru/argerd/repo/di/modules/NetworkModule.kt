package ru.argerd.repo.di.modules

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.argerd.repo.data.network.NetworkApi
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getApi(): NetworkApi {
        return Retrofit.Builder()
                .baseUrl("https://mentoring-35da2.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetworkApi::class.java)
    }
}