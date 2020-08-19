package com.hookiz.youtuber.server

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.hookiz.youtuber.objects.Playlist
import com.hookiz.youtuber.objects.VideoItem
import com.hookiz.youtuber.room.AppDatabase
import com.hookiz.youtuber.server.response.PlaylistListResponse
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "Repository"

class Repository( application : Application ) {

    private val playlistDao = AppDatabase.get( application ).playlistDao()
    private val videoItemDao = AppDatabase.get( application ).videoItemDao()

    private var mApiService: ApiService
    init {
        Log.d( TAG, "init()" )
        mApiService = ApiClient.getClient().create( ApiService::class.java )
    }

    fun fetchAllPlaylists() {
        Log.d( TAG, "fetchAllPlaylists()" )
        mApiService.fetchAllPlaylists()?.enqueue( object : Callback<PlaylistListResponse?> {
            override fun onFailure(call: Call<PlaylistListResponse?>, t: Throwable? ) {
                Log.d( TAG, "fetchAllPlaylists onFailure() --> $t" )
            }

            override fun onResponse(call: Call<PlaylistListResponse?>, response: Response<PlaylistListResponse?>) {
                val scope = CoroutineScope( Dispatchers.Default )
                scope.launch {
                    if ( response.code() == 200 ) {
                        val playlists : MutableList<Playlist> = mutableListOf()
                        val videoItems : MutableList<VideoItem> = mutableListOf()
                        response.body()?.items?.forEach { playlist ->
                            playlist.let {
                                Log.d( TAG, "fetchAllPlaylists onResponse() playlists --> $it" )
                                val list = Playlist( it.id, it.playlistItems, it.snippet )
                                playlists.add( list )

                                playlist.playlistItems.items.forEach { videoItem ->
                                    val item = VideoItem( videoItem.id, videoItem.snippet )
                                    videoItems.add( item )
                                }
                            }
                        }
                        playlistDao.insert( playlists )
                        videoItemDao.insert( videoItems )
                    }
                }
            }
        })
    }

    fun getAllPlaylists() : LiveData<List<Playlist>> {
        Log.d( TAG, "getAllPlaylists()" )
        return playlistDao.getAllPlaylists()
    }

    fun getAllVideosForPlaylistId( id : String ) : LiveData<List<VideoItem>> {
        Log.d( TAG, "fetchAllVideosForPlaylistId( $id )" )
        return videoItemDao.getAllVideosForPlaylistId( id )
    }

    fun updateVideoItem( videoItem : VideoItem ){
        val scope = CoroutineScope( Dispatchers.Default )
        scope.launch {
            videoItemDao.updateVideoItem( videoItem )
        }
    }

    fun deleteAllVideoItems() {
        val scope = CoroutineScope( Dispatchers.Default )
        scope.launch {
            videoItemDao.deleteAll()
        }
    }

}