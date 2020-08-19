package com.hookiz.youtuber.server

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class ApiClient {

    companion object {

        private const val TAG = "ApiClient"
        private lateinit var retrofit: Retrofit
        private lateinit var okHttpClient: OkHttpClient

        @Singleton
        fun getClient(): Retrofit {
            Log.d(TAG, "getClient() ")
            if ( ! ::okHttpClient.isInitialized ) {
                initOkHttp()
            }
            if ( ! ::retrofit.isInitialized ) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory( GsonConverterFactory.create() )
                    .baseUrl( "https://landing.cal-online.co.il/" )
                    .client( okHttpClient )
                    .build()
            }
            return retrofit
        }

        private fun initOkHttp() {
            Log.d( TAG, "initOkHttp() ")
            val client = OkHttpClient().newBuilder()
                .connectTimeout( 60.toLong(), TimeUnit.SECONDS )
                .readTimeout( 60.toLong(), TimeUnit.SECONDS )
                .writeTimeout( 60.toLong(), TimeUnit.SECONDS )
            client.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                val request = requestBuilder.build()
                chain.proceed( request )
            }
            okHttpClient = client.build()
        }
    }

}