package com.hookiz.youtuber.objects

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hookiz.youtuber.objects.convertors.ResourceIdConverter
import com.hookiz.youtuber.objects.convertors.ThumbnailsConverter

@TypeConverters( ResourceIdConverter::class, ThumbnailsConverter::class )
@Entity( tableName = "playlist_table" )
class Playlist constructor(
    @PrimaryKey var id : String,
    var title : String?,
    var description : String?,
    var channelId : String?,
    var channelTitle : String?,
    var playlistId : String?,
    var position : Int?,
    var thumbnails : Thumbnails?

) {
    constructor( id : String, playlistItems : PlaylistItems, snippet : Snippet? )
            : this(
        id,
        snippet?.title,
        snippet?.description,
        snippet?.channelId,
        snippet?.channelTitle,
        snippet?.playlistId,
        snippet?.position,
        snippet?.thumbnails
    ) {
        this.id = id
//        this.publishedAt = snippet?.publishedAt
//        this.title = snippet?.title
//        this.description = snippet?.description
//        this.channelId = snippet?.channelId
//        this.channelTitle = snippet?.channelTitle
//        this.playlistId = snippet?.playlistId
//        this.position = snippet?.position
//        this.resourceId = snippet?.resourceId
//        this.thumbnails = snippet?.thumbnails
    }

    // it.playlistItems, it.snippet

    @Ignore lateinit var playlistItems : PlaylistItems
    @Ignore lateinit var snippet : Snippet

    override fun toString(): String {
        return "{ " +
                "id : $id, " +
                "title : $title, " +
                "description : $description, " +
                "channelId : $channelId, " +
                "channelTitle : $channelTitle, " +
                "playlistId : $playlistId, " +
                "position : $position, " +
                "thumbnails : $thumbnails, " +
                "}"
    }
}