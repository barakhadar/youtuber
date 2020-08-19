package com.hookiz.youtuber.objects

import androidx.room.TypeConverters
import com.hookiz.youtuber.objects.convertors.ThumbnailConverter

@TypeConverters( ThumbnailConverter::class )
class Thumbnails(
    val default : Thumbnail,
    val medium : Thumbnail,
    val high : Thumbnail,
    val standard : Thumbnail,
    val maxres : Thumbnail
) {
}