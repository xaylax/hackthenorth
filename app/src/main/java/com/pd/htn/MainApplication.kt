package com.pd.htn

import android.app.Application
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MainApplication : Application() {
    companion object {
        private val httpClient = OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization", BuildConfig.TD_API_KEY)
                .build()

            it.proceed(request)
        }.build()

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.TD_ENDPOINT)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        val retrofit2 = Retrofit.Builder()
            .baseUrl(BuildConfig.R_ENDPOINT)
            .build()
    }



    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}