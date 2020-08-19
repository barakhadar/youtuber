package com.hookiz.youtuber.dagger

import com.hookiz.youtuber.objects.App
import com.hookiz.youtuber.ui.viewmodel.PlaylistsActivityViewModel
import com.hookiz.youtuber.ui.viewmodel.VideoItemsActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ AppModule::class ] )
interface AppComponent {
    fun inject( app : App )
    fun inject( playlistsActivityViewModel : PlaylistsActivityViewModel )
    fun inject( videoItemsActivityViewModel : VideoItemsActivityViewModel )
}