package com.hookiz.youtuber.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hookiz.youtuber.objects.Playlist
import com.hookiz.youtuber.objects.VideoItem

@Database( entities = [ Playlist::class, VideoItem::class ], version = 1, exportSchema = false )
abstract class AppDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlaylistDao
    abstract fun videoItemDao(): VideoItemDao

    companion object {
        @Volatile
        private var DB_INSTANCE: AppDatabase? = null

        fun get( context: Context ) : AppDatabase {
            return DB_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                DB_INSTANCE = instance
                instance
            }
        }

    }
}