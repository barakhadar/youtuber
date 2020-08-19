package com.hookiz.youtuber.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hookiz.youtuber.objects.Playlist

@Dao
abstract class PlaylistDao {

    @Delete
    abstract fun delete( playlist : Playlist)

    @Query("DELETE FROM playlist_table")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert( playlist : Playlist )

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert( playlist : List<Playlist> )

    @Update
    abstract fun updatePlaylist( playlist : Playlist )

    @Query("SELECT * FROM playlist_table" )
    abstract fun getAllPlaylists(): LiveData<List<Playlist>>

    @Query("SELECT * FROM playlist_table WHERE id = :id" )
    abstract fun getPlaylistById( id : String ): Playlist?

}