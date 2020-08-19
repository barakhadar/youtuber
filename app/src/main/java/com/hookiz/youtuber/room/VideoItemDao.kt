package com.hookiz.youtuber.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hookiz.youtuber.objects.VideoItem

@Dao
abstract class VideoItemDao {

    @Delete
    abstract fun delete( videoItem : VideoItem)

    @Query("DELETE FROM videoItem_table")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert( videoItem : VideoItem )

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert( videoItem : List<VideoItem> )

    @Update
    abstract fun updateVideoItem( videoItem : VideoItem )

    @Query("SELECT * FROM videoItem_table" )
    abstract fun getAllVideoItem(): LiveData<List<VideoItem>>

    @Query("SELECT * FROM videoItem_table WHERE playlistId = :playlistId" )
    abstract fun getAllVideosForPlaylistId( playlistId : String ): LiveData<List<VideoItem>>

}