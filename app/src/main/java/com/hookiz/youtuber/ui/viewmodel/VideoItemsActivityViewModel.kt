package com.hookiz.youtuber.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hookiz.youtuber.objects.VideoItem
import com.hookiz.youtuber.server.Repository
import javax.inject.Inject

class VideoItemsActivityViewModel( application : Application ) : AndroidViewModel( application ) {

    private val TAG = "ContactsViewModel"
    @Inject lateinit var repository : Repository
    private lateinit var playlistId : String
    val videoItems : LiveData<List<VideoItem>> by lazy { repository.getAllVideosForPlaylistId( playlistId ) }

    fun initLiveData( playlistId : String ) {
        this.playlistId = playlistId
    }

}