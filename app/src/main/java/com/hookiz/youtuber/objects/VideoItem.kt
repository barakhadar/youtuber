package com.hookiz.youtuber.objects

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hookiz.youtuber.objects.convertors.ResourceIdConverter
import com.hookiz.youtuber.objects.convertors.ThumbnailsConverter

@TypeConverters( ResourceIdConverter::class, ThumbnailsConverter::class )
@Entity( tableName = "videoItem_table" )
class VideoItem (
    @PrimaryKey var id : String,
    var publishedAt : String?,
    var title : String?,
    var description : String?,
    var channelId : String?,
    var channelTitle : String?,
    var playlistId : String?,
    var position : Int?,
    var resourceId : ResourceId?,
    var thumbnails : Thumbnails?
) {

    constructor( id : String, snippet : Snippet? )
            : this(
        id,
        snippet?.publishedAt,
        snippet?.title,
        snippet?.description,
        snippet?.channelId,
        snippet?.channelTitle,
        snippet?.playlistId,
        snippet?.position,
        snippet?.resourceId,
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

    @Ignore lateinit var snippet : Snippet
}