package com.hookiz.youtuber.objects

import android.app.Application
import com.hookiz.youtuber.dagger.AppComponent
import com.hookiz.youtuber.dagger.AppModule
import com.hookiz.youtuber.dagger.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule( AppModule( this ) )
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject( this )
    }

}

