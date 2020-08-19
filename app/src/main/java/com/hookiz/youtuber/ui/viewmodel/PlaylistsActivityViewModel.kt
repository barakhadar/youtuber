package com.hookiz.youtuber.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hookiz.youtuber.objects.Playlist
import com.hookiz.youtuber.server.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistsActivityViewModel( application : Application ) : AndroidViewModel( application ) {

    private val TAG = "PlaylistsViewModel"
    @Inject lateinit var repository : Repository
    val playlists : LiveData<List<Playlist>> by lazy { repository.getAllPlaylists() }

    fun initLiveData() {
        viewModelScope.launch {
            repository.fetchAllPlaylists()
        }
    }

}