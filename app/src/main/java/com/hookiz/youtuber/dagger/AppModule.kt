package com.hookiz.youtuber.dagger

import com.hookiz.youtuber.objects.App
import com.hookiz.youtuber.server.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule( var app: App ) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideRepository() = Repository( app )
}