package com.hookiz.youtuber.objects.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hookiz.youtuber.objects.ResourceId
import com.hookiz.youtuber.objects.Thumbnail
import com.hookiz.youtuber.objects.Thumbnails

class ThumbnailConverter {

    @TypeConverter
    fun toThumbnail( json : String ) : Thumbnail {
        val type = object : TypeToken<Thumbnail>() {}.type
        return Gson().fromJson( json.trim(), type )

    }

    @TypeConverter
    fun toString( thumbnail : Thumbnail ) : String {
        return Gson().toJson( thumbnail )
    }

}